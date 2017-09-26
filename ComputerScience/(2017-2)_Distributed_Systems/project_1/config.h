#ifndef CONFIG_H
#define CONFIG_H

#include <stdio.h>
#include <assert.h>
#include <string.h>
#include <stdlib.h>


typedef struct {
    char address_mb[100];
    char address_mq[100];
    int port_mb;
    int port_mq;
    char pid;
} client_config_t;



typedef struct {
    char address[100];
    int port;
    char pid;
} server_config_t;



#endif