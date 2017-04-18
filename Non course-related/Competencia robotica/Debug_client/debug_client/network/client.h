#ifndef CLIENT_H
#define CLIENT_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <string>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <iostream>

class Client
{
private:
    static const int max_data_size=100;             //información del puerto y tamaño de datos
    unsigned int stream_descriptor, num_bytes, port;         //ficheros descriptores
    char buf[max_data_size];                        //sctruct donde se recive la información
    struct hostent *he;                             //struct que recive información sobre el nodo
    struct sockaddr_in server;                      //información sobre la dirección del servidor

public:
    Client();
    Client(char *server_ip = "localhost", int port = 3557);
    int recieve_from_server(void *data);
    void send_to_server(void  *data);
    void close_connection();
    virtual ~Client();
};

#endif // CLIENT_H
