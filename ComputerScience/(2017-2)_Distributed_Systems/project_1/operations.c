#ifndef OPERATIONS_C
#define OPERATIONS_C

#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <assert.h>
#include <string.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#include "operations.h"
#include "config.c"


void cpy_message(message_t *dest, message_t *src){
    dest->container = src->container;
    dest->destination = src->destination;
    dest->id= src->id;
    memcpy(dest->message, src->message, src->size * sizeof(char));
    dest->operation = src->operation;
    dest->size = src->size;
}

void clr_message(message_t *message){
    message->container = 0;
    message->destination = 0;
    message->id= 0;
    message->operation = 0;
    message->size = 0;
}

void print_message(message_t *message){
    //printf("message->container = %d\n",message->container);
    //printf("message->destination = %d\n",message->destination);
    //printf("message->id = %d\n",message->id);
    //printf("message->operation = %d\n",message->operation);
    //printf("message->size = %d\n",message->size);
}

/*
 * Sends a job to a messagebox. Just plainly sends the message to be processed
 * as a struct and then returns the unique identifier of the task.
 */
int mb_put(int mb_id, char *msg, int msg_size, int dest, server_config_t *config){
    int ssocket, numbytes = 0;
    struct sockaddr_in server;
    
    assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
    struct hostent *he = gethostbyname(config->address);
    server.sin_family = AF_INET;
    server.sin_port = htons(config->port);
    server.sin_addr = *((struct in_addr *)he->h_addr);
    bzero(&(server.sin_zero), 8);
    assert(connect(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);


    // prepare_connection:{
    //     size_t operation = MB_PUT;
    //     assert(send(ssocket, &operation, sizeof(operation), 0));
    // }

    // at this point message.id is not set, since it's set on the server.
    send_message:{
        message_t message;
        message.destination = dest;
        message.size = msg_size;
        message.container = mb_id;
        message.operation = MB_PUT;
        memcpy(message.message, msg, sizeof(char)*msg_size);
        assert(send(ssocket, &message, sizeof(message_t), 0));
    }

    recieve_identifier:{
        size_t uuid = 0;
        assert((numbytes = recv(ssocket, &uuid, sizeof(size_t), 0)) != -1);
        close(ssocket); /* cerramos ssocket =) */
        return uuid;
    }
};


/*
 * Retrieves a job from a certain message queue.
 */
int mb_get(int mb_id, char *msg, int *msg_size, int dest, server_config_t *config){
    int ssocket, numbytes = 0;
    msg_size[0] = 0;
    struct sockaddr_in server;
    
    assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
    struct hostent *he = gethostbyname(config->address);
    server.sin_family = AF_INET;
    server.sin_port = htons(config->port);
    server.sin_addr = *((struct in_addr *)he->h_addr);
    bzero(&(server.sin_zero), 8);
    assert(connect(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);

    
    // prepare_connection:{
    //     size_t operation = MB_GET;
    //     assert(send(ssocket, &operation, sizeof(operation), 0));
    // }

    // at this point message.id is not set, since it's set on the server.
    send_message:{
        message_t message;
        message.destination = dest;
        message.container = mb_id;
        message.operation = MB_GET;
        assert(send(ssocket, &message, sizeof(message_t), 0));
    }

    recieve_identifier:{
        message_t message;
        clr_message(&message);
        assert((numbytes = recv(ssocket, &message, sizeof(message_t), MSG_WAITALL)) != -1);
        print_message(&message);
        memcpy(msg, message.message, sizeof(char)*message.size);
        // msg = message.message;
        msg_size[0] = message.size;
        //printf("TAMAÑO MENSAJE: %d", message.size);
        close(ssocket); /* cerramos ssocket =) */
        return message.id;
    }
};


int mq_put(int mq_id, char *msg, int msg_size, server_config_t *config){
    int ssocket, numbytes = 0;
    struct sockaddr_in server;
    
    assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
    struct hostent *he = gethostbyname(config->address);
    server.sin_family = AF_INET;
    server.sin_port = htons(config->port);
    server.sin_addr = *((struct in_addr *)he->h_addr);
    bzero(&(server.sin_zero), 8);
    assert(connect(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);


    // prepare_connection:{
    //     size_t operation = MQ_PUT;
    //     assert(send(ssocket, &operation, sizeof(operation), 0));
    // }

    // at this point message.id is not set, since it's set on the server.
    send_message:{
        message_t message;
        message.size = msg_size;
        message.container = mq_id;
        message.operation = MQ_PUT;
        message.destination = 0;
        message.id = 0;
        memcpy(message.message, msg, sizeof(char)*msg_size);
        assert(send(ssocket, &message, sizeof(message_t), 0));
    }

    recieve_identifier:{
        size_t uuid = 0;
        assert((numbytes = recv(ssocket, &uuid, sizeof(size_t), 0)) != -1);
        //printf("UUID?=%zd", uuid);
        close(ssocket); /* cerramos ssocket =) */
        return uuid;
    }  
};

int mq_get(int mq_id, char *msg, int *msg_size, server_config_t *config){
    int ssocket, numbytes = 0;
    struct sockaddr_in server;
    
    assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
    struct hostent *he = gethostbyname(config->address);
    server.sin_family = AF_INET;
    server.sin_port = htons(config->port);
    server.sin_addr = *((struct in_addr *)he->h_addr);
    bzero(&(server.sin_zero), 8);
    assert(connect(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);

    
    // prepare_connection:{
    //     size_t operation = MQ_GET;
    //     assert(send(ssocket, &operation, sizeof(operation), 0));
    // }

    // at this point message.id is not set, since it's set on the server.
    send_message:{
        message_t message;
        message.container = mq_id;
        message.operation = MQ_GET;
        assert(send(ssocket, &message, sizeof(message_t), 0));
    }

    recieve_identifier:{
        message_t message;
        clr_message(&message);

        assert((numbytes = recv(ssocket, &message, sizeof(message_t), MSG_WAITALL)) != -1);
        //printf("recievedbytes: %d\n", numbytes);
        //printf("message: %s\n", message.message);
        print_message(&message);
        memcpy(msg, message.message, sizeof(char)*message.size);
        msg_size[0] = message.size;
        close(ssocket); /* cerramos ssocket =) */
        return message.id;
    }
};

int mq_delete(int mq_id, int msg_id, server_config_t *config){
    int ssocket, numbytes = 0;
    struct sockaddr_in server;
    
    assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
    struct hostent *he = gethostbyname(config->address);
    server.sin_family = AF_INET;
    server.sin_port = htons(config->port);
    server.sin_addr = *((struct in_addr *)he->h_addr);
    bzero(&(server.sin_zero), 8);
    assert(connect(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);

    
    // prepare_connection:{
    //     size_t operation = MQ_DELETE;
    //     assert(send(ssocket, &operation, sizeof(operation), 0));
    // }

    // at this point message.id is not set, since it's set on the server.
    send_message:{
        message_t message;
        message.container = mq_id;
        message.id = msg_id;
        message.operation = MQ_DELETE;
        assert(send(ssocket, &message, sizeof(message_t), 0));
    }

    recieve_identifier:{
        message_t message;
        assert((numbytes = recv(ssocket, &message, sizeof(message_t), MSG_WAITALL)) != -1);
        close(ssocket); /* cerramos ssocket =) */
        return message.id;
    }
};

/*
 * ROT13 ENCODER-DECODER
 * Copied from stackoverflow to favour understanding
 * https://stackoverflow.com/questions/13520067/about-rot13-implementation
 */
void rot13 (char *s) {
    if (s == NULL)
        return;
    int i;
    for (i = 0; s[i]; i++) {
        if (s[i] >= 'a' && s[i] <= 'm') { s[i] += 13; continue; }
        if (s[i] >= 'A' && s[i] <= 'M') { s[i] += 13; continue; }
        if (s[i] >= 'n' && s[i] <= 'z') { s[i] -= 13; continue; }
        if (s[i] >= 'N' && s[i] <= 'Z') { s[i] -= 13; continue; }
    }
};



#endif