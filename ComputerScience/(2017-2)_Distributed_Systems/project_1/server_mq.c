#define DEBUG

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
#include <time.h>
#include <limits.h>
#include <stdlib.h>
#include <stdbool.h>
#include <errno.h>

#include "config.c"
#include "operations.c"
#include <assert.h>

#define VECTOR_INITIAL_SIZE 100
server_config_t config;

typedef struct {
    message_t *data;
    size_t capacity;
    size_t size;
} message_vector_t;

void init_vector(message_vector_t *vector, size_t capacity){
    vector->capacity = capacity;
    vector->size = 0;
    vector->data = malloc(sizeof(message_t) * capacity);
}

void push_back_vector(message_vector_t *vector, message_t message){
    check_size: if(vector->capacity == vector->size){
        vector->capacity *= 2;
        realloc(vector->data, sizeof(message_t) * vector->capacity);
    };
    vector->data[vector->size] = message;
    vector->size++;
}

void delete_from_vector(message_vector_t *vector, size_t idx){
    printf("deleting index %d\n", idx);
    for(int i=idx; i<vector->size; i++){
        vector->data[i] = vector->data[i+1];
    }
    vector->size--;
};


int main(int argc, char *argv[]){
    parse_arguments_as_server_config(&config, argc, argv);
    srand(time(NULL));

    int ssocket, csocket, queue = 100;
    struct sockaddr_in server;
    struct sockaddr_in client;
    assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
    server.sin_family = AF_INET;
    server.sin_port = htons(config.port);
    server.sin_addr.s_addr = INADDR_ANY;
    bzero(&(server.sin_zero), 8);
    printf("Binding...\n");
    assert(bind(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);
    printf("Listening...\n");
    assert(listen(ssocket, queue) != -1);
    printf("Starting server...\n");

    message_vector_t mq_vector, mb_vector;
    init_vector(&mq_vector, VECTOR_INITIAL_SIZE);
    init_vector(&mb_vector, VECTOR_INITIAL_SIZE);

    processing: while (1!=0) {
        int sin_size = sizeof(struct sockaddr_in), numbytes = 4;
        assert((csocket = accept(ssocket, (struct sockaddr *)&client, &sin_size)) != -1);

        get_message_struct: {
            message_t buf;
            assert((numbytes = recv(csocket, &buf, sizeof(message_t), 0)) != -1);
            printf("Recieved: %s", buf.message);

            // MESSAGE QUEUE GET OPERATION
            if(buf.operation == MQ_GET){
                message_t resp_message;
                printf("OPERATION: MQ_GET\n");
                size_t index = -1;
                for(int i=0; i<mq_vector.size; i++){
                    if(mq_vector.data[i].container == buf.container){
                        index = i;
                        break;
                    }
                }

                size_t response[1];
                response[0] = (index == -1) ? 0 : mq_vector.data[index].id; 

                if(response[0] > 0){
                    int numbytes = 0;
                    cpy_message(&resp_message, &mq_vector.data[index]);
                    print_message(&resp_message);

                    numbytes = send(csocket, &resp_message, sizeof(message_t), 0);

                    //printf("numbytes sent:%d, error?=%d", numbytes,errno);

                    assert(numbytes > 0);
                    // assert(send(csocket, &response[0], sizeof(size_t), 0));
                }
                else if(response[0] == 0){
                    clr_message(&resp_message);
                    resp_message.id = 0;
                    print_message(&resp_message);
                    assert(send(csocket, &resp_message, sizeof(message_t), 0));
                    // assert(send(csocket, &response[0], sizeof(size_t), 0));
                }
            }

            // MESSAGE MAILBOX GET OPERATION
            else if(buf.operation == MB_GET){
                message_t resp_message;
                printf("OPERATION: MB_GET\n");
                size_t index = -1;
                for(int i=0; i<mb_vector.size; i++){
                    if( mb_vector.data[i].container == buf.container &&
                        mb_vector.data[i].destination == buf.destination){
                        index = i;
                        break;
                    }
                }

                size_t response[1];
                response[0] = (index == -1) ? 0 : mb_vector.data[index].id; 

                if(response[0] > 0){
                    cpy_message(&resp_message, &mb_vector.data[index]);
                    print_message(&resp_message);
                    assert(send(csocket, &resp_message, sizeof(message_t), 0));
                    // assert(send(csocket, &response[0], sizeof(size_t), 0));
                    delete_from_vector(&mb_vector, index); // Destruct operation
                }
                else if(response[0] == 0){
                    
                    clr_message(&resp_message);
                    buf.id = 0;
                    print_message(&resp_message);
                    assert(send(csocket, &resp_message, sizeof(message_t), 0));
                    // assert(send(csocket, &response[0], sizeof(size_t), 0));
                }
            }


            // MESSAGE QUEUE PUT OPERATION
            else if(buf.operation == MQ_PUT){
                printf("OPERATION: MQ_PUT\n");
                buf.id = rand() + 1;
                size_t response[1];
                response[0] = buf.id;
                push_back_vector(&mq_vector, buf);
                print_message(&buf);
                // assert(send(csocket, &buf, sizeof(message_t), 0));
                assert(send(csocket, &response, sizeof(size_t), 0));
            }

            // MESSAGE BOX PUT OPERATION
            else if(buf.operation == MB_PUT){
                printf("OPERATION: MB_PUT\n");
                buf.id = rand() + 1;
                size_t response[1];
                response[0] = buf.id;
                push_back_vector(&mb_vector, buf);
                print_message(&buf);
                // assert(send(csocket, &buf, sizeof(message_t), 0));
                assert(send(csocket, &response, sizeof(size_t), 0));
            }


            // MESSAGE QUEUE DELETE OPERATION
            else if(buf.operation == MQ_DELETE){
                printf("OPERATION: MQ_DELETE\n");
                for(int i=0; i<mq_vector.size; i++){
                    if( mq_vector.data[i].container == buf.container &&
                        mq_vector.data[i].id == buf.id){
                        delete_from_vector(&mq_vector, i);
                        break;
                    }
                }
                size_t response[1];
                response[0] = 0;
                print_message(&buf);
                // assert(send(csocket, &buf, sizeof(message_t), 0));
                assert(send(csocket, &response, sizeof(size_t), 0));
            }
        }
        assert(close(csocket) != -1);
    }
    return 0;
};