#include "serverthread.h"
#include <iostream>
#include <string>
#include <sstream>


void ServerThread::run(){
    this->engaged = false;
    emit printStatusData("Starting server...");
    this->startServerAndListen();

    int recieved_bytes = 0;
    if(this->serverInstance->hasErrors() == -1){
        emit printStatusData("Error.");
        this->engaged = false;
        return;

    }

    while(engaged){
        //std::cout << "Something's fishy around here...." << std::endl;
        std::Server::magdata_t data;

        recieved_bytes = this->serverInstance->recieve_from_client(this->current_client, &data);
        while( recieved_bytes > 0
              && this->engaged){
            recieved_bytes = this->serverInstance->recieve_from_client(this->current_client, &data);
//            emit printDebugData("x: " +  QString::number(data.x));
//            emit printDebugData("y: " +  QString::number(data.y));
//            emit printDebugData("z: " +  QString::number(data.z));
            emit sendData(data.x, data.y, data.z);
        }

        emit printStatusData("Error on connection.");
        this->serverInstance->close_client(this->current_client);
        this->startServerAndListen();
    }


    engaged = false;
    emit printStatusData("Server disengaged...");
}

void ServerThread::startServerAndListen(){
    if(this->engaged != true){
        emit printStatusData("Engaging server...");
        this->serverInstance = new std::Server(port);
    }
    this->engaged = true;
    emit printStatusData("Listening over...");
    this->current_client = this->serverInstance->wait_for_a_client();
    emit printStatusData("Client found...");
}

bool ServerThread::isEngaged(){
    return this->engaged;
}

void ServerThread::disengage(){
    this->engaged = false;
}

void ServerThread::setPort(int port){
    this->port = port;
}
