/*
 * Client.cpp
 *
 *  Created on: Feb 28, 2014
 *      Author: jvarred
 */

#include "Client.h"
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

namespace std {

Client::Client(char *server_ip, int port) {
    //init secuence
    this->port = port;

    std::cout << "toc toc!? "<< port << std::endl;
    //se recupera el nombre del servidor
    he = gethostbyname(server_ip);
    if (he == NULL) {
        printf("gethostbyname() error\n");
        exit(-1);
    }

    //al descriptor de archivos se le entrega el origen del stream
    //cabe recordar que el stream de un socket, es identico al de un archivo,
    //solo que con la diferencia de que acá vas leyendo en una red, en vez de un
    //flujo de datos estándar.
    //AF_INET = IP
    //SOCK_STREAM = TCP
    stream_descriptor = socket(AF_INET, SOCK_STREAM, 0);

    if (stream_descriptor == -1) {
        /* llamada a socket() */
        printf("socket() error\n");
        exit(-1);
    }

    //descripcion del tipo de servidor de entrada y puerto
    server.sin_family = AF_INET;
    server.sin_port = htons(port);
    //se toma la dirección almacenada en he y se agrega a la direccion del servidor
    server.sin_addr = *((struct in_addr *) he->h_addr);
    //se setean los primeros 8 bits en 0... ni idea porqué. aunque se puede usar memset 0
    bzero(&(server.sin_zero), 8);

    //se intenta conectar el descriptor de archivos al servidor.
    //como hay más de un tipo, tambien es necesario especificar el tamaño. ¡DUH!
    int result = connect(stream_descriptor, (struct sockaddr *) &server, sizeof(struct sockaddr));
    if (result == -1) {
        /* llamada a connect() */
        printf("connect() error\n");
        exit(-1);
    }

}

Client::~Client() {
}

void Client::send_to_server(std::magdata_t *data) {
    send(stream_descriptor, data, sizeof(std::magdata_t), 0);
}

int Client::recieve_from_server(std::magdata_t *data) {
    int num_bytes = recv(stream_descriptor, data, sizeof(std::magdata_t), 0);

    if (num_bytes == -1) {
        /* llamada a recv() */
        printf("Error en recv() \n");
        return num_bytes;
    }

    if (num_bytes == 0) {
        /* llamada a recv() */
        printf("Conexion terminada\n");
        close(stream_descriptor);
        return num_bytes;
    }
}

void Client::close_connection() {
    close(stream_descriptor);
}

} /* namespace std */
