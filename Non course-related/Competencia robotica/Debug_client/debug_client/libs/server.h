#ifndef SERVER_H
#define SERVER_H

#endif // SERVER_H
#ifndef SERVER_H_
#define SERVER_H_
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <iostream>
#include "libs/server.h"

namespace std {




class Server {





    private:
        static const int max_data_size = 100, backlog = 2;  //información del puerto y tamaño de datos
        int server_fd, client_fd, num_bytes, sin_size, port, errors;     //ficheros descriptores
        char buf[max_data_size];                            //sctruct donde se recive la información
        struct hostent *he;                                 //struct que recive información sobre el nodo
        struct sockaddr_in server, client;                  //información sobre la dirección del servidor y cliente

    public:
        typedef struct lsm303_t {
                double x;
                double y;
                double z;
        } magdata_t;

        Server();
        Server(int port);
        int hasErrors();
        void sayHello();
        void close_client(int client);
        int wait_for_a_client();
        int current_connection;
        int recieve_from_client(int client, Server::magdata_t *data);
        void send_to_client(int client, Server::magdata_t *data);
        int recieve_from_client(Server::magdata_t *data);
        void send_to_client(Server::magdata_t *data);
        virtual ~Server();
};

} /* namespace std */

#endif /* SERVER_H_ */
