/*
 * Server.cpp
 *
 *  Created on: Mar 2, 2014
 *      Author: jvarred
 */

#include "libs/server.h"

namespace std {

void Server::sayHello() {
    std::cout << "HELLO! " << std::endl;
}

Server::Server() {

}

//init procedures
Server::Server(int port) {

    int set = 1;
    server_fd = socket(AF_INET, SOCK_STREAM, 0);

    //verificamos errores
    if (server_fd == -1) {
        printf("error en socket()\n");
        this->errors = -1; return;
    }

    //creamos las especificaciones del socket
    server.sin_family = AF_INET;
    server.sin_port = htons(port);
    server.sin_addr.s_addr = INADDR_ANY;

    //seteamos opciones
    int result = setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR, &set, sizeof(set));
    if (result == -1) {
        printf("error en bind() \n");
        this->errors = -1; return;
    }

    //bzero(&(server.sin_zero), 8);

    //ligamos este socket a este computador en ese puerto
    result = bind(server_fd, (struct sockaddr*) &server, sizeof(struct sockaddr));
    if (result == -1) {
        printf("error en bind() \n");
        this->errors = -1; return;
    }

    //se le da la instruccion para que comienze a escuchar.
    result = listen(server_fd, backlog);
    if (result == -1) {
        printf("error en listen()\n");
        this->errors = -1; return;
    }

    //OK
}

int Server::hasErrors() {
    return (this->errors<0? true: false);
}


int Server::wait_for_a_client() {

    //se toma el tamaño del socket
    sin_size = sizeof(struct sockaddr_in);



    this->client_fd = accept(server_fd, (struct sockaddr *) &client, (socklen_t*) &sin_size);
    if (client_fd == -1) {
        printf("error en accept()\n");
        return -1;
    }

    printf("Se obtuvo una conexión desde %s\n", inet_ntoa(client.sin_addr));

    return client_fd;
}

void Server::send_to_client(int client_fd_param, std::Server::magdata_t *data) {

    send(client_fd_param, data, sizeof(std::Server::magdata_t), 0);
}

int Server::recieve_from_client(int client_fd_param, std::Server::magdata_t *data) {
    int num_bytes = recv(client_fd_param, data, sizeof(std::Server::magdata_t), 0);


    if (num_bytes == -1) {
        /* llamada a recv() */
        printf("Error en recv() \n");
        return num_bytes;
    }

    if (num_bytes == 0) {
        /* llamada a recv() */
        printf("Conexion terminada\n");
        //close(server_fd); //CHUCHETUMARE!
        return num_bytes;
    }
}


void Server::send_to_client(magdata_t *data) {
    send(this->client_fd, data, sizeof(magdata_t), 0);
}

int Server::recieve_from_client(magdata_t *data) {
    int num_bytes = recv(this->client_fd, buf, sizeof(magdata_t), 0);


    if (num_bytes == -1) {
        /* llamada a recv() */
        printf("Error en recv() \n");
        return num_bytes;
    }

    if (num_bytes == 0) {
        /* llamada a recv() */
        printf("Conexion terminada\n");
        //close(server_fd);  //ESTA COSA ME COSTÓ DOS SEMANAS ENCONTRAR EN EL PUTO CÓDIGO!
        return num_bytes;
    }
}

void Server::close_client(int client_fd_param) {
    close(client_fd_param);
}

Server::~Server() {
    // TODO Auto-generated destructor stub
}

} /* namespace std */

/*
Server::Server(int port) {

    this->port = port;

    std::cout << "stage1..." << std::endl;
    //creamos un socket
    fd = socket(AF_INET, SOCK_STREAM, 0);
    if (fd == -1) {
        printf("error en socket()\n");
//        exit(-1);
    }

    //creamos las especificaciones del socket
    server.sin_family = AF_INET;
    server.sin_port = htons(port);
    server.sin_addr.s_addr = INADDR_ANY;
    bzero(&(server.sin_zero), 8);

    //ligamos este socket a este computador en ese puerto
    int result = bind(fd, (struct sockaddr*) &server, sizeof(struct sockaddr));
    if (result == -1) {
        printf("error en bind() \n");
//        exit(-1);
    }

    //se le da la instruccion para que comienze a escuchar.
    result = listen(fd, backlog);
    if (result == -1) {
        printf("error en listen()\n");
//        exit(-1);
    }
}

int Server::wait_for_a_client() {
    //se toma el tamaño del socket
    sin_size = sizeof(struct sockaddr_in);

    fd2 = accept(fd, (struct sockaddr *) &client, (socklen_t*) &sin_size);
    if (fd2 == -1) {
        printf("error en accept()\n");
//        exit(-1);
    }

    printf("Se obtuvo una conexión desde %s\n", inet_ntoa(client.sin_addr));

    return fd2;
}

void Server::send_to_client(int client, string &data) {
    send(fd2, data.c_str(), data.length(), 0);
}

int Server::recieve_from_client(int client, string &data) {
    int num_bytes = recv(client, buf, max_data_size, 0);

    data = buf;

    if (num_bytes == -1) {
        // llamada a recv()
        printf("Error en recv() \n");
        return num_bytes;
    }

    if (num_bytes == 0) {
        // llamada a recv()
        printf("Conexion terminada\n");
        close(fd);
        return num_bytes;
    }
}

void Server::close_client(int client){
    close(client);
}

Server::~Server() {
    // TODO Auto-generated destructor stub
}

} // namespace std
*/
