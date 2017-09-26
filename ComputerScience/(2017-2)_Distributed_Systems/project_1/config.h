#ifndef CONFIG_H
#define CONFIG_H

#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <assert.h>
#include <string.h>
#include <pthread.h>
#include <stdlib.h>


typedef struct {
    char address_mb[100];
    char address_mq[100];
    int port_mb;
    int port_mq;
} client_config_t;



typedef struct {
    char address[100];
    int port;
} server_config_t;


// There is nothing specified that we should recieve the pid of
// target and destination, but well, here we are.
typedef struct {
    char message[1024];
    char dst_pid;
    char src_pid;
} message_t;

#endif