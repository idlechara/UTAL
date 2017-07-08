#ifndef SERVERTHREAD_H
#define SERVERTHREAD_H
#include <QString>
#include <string>
#include <QThread>
#include <libs/server.h>


class ServerThread : public QThread
{
    Q_OBJECT
signals:
    void printStatusData(QString data);
    void printDebugData(QString data);
    void sendData(double x, double y, double z);

private:
    void run();
    bool engaged;
    int port, current_client;
    void startServerAndListen();

public:
    std::Server *serverInstance;
    std::string data;
    bool isEngaged();
    void disengage();
    void setPort(int port);
};

#endif // SERVERTHREAD_H
