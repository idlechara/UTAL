#ifndef CONFIG_C
#define CONFIG_C

#include "config.h"

// Parses configuration with format host:port using command line arguments
void parse_arguments_as_client_config(client_config_t *config, int argc, char *argv[]){
    if(argc != 3){
        printf("Arguments invalid.");
        exit(0);
    }

    message_box_parsing: for(int i=0; i<strlen(argv[1]); i++){
        if( argv[1][i] != ':'){
            config->address_mb[i] = argv[1][i];
        }
        else{
            config->address_mb[i] = '\0';
            char *port_val = argv[1];
            config->port_mb = atoi(port_val+i+1);
            #ifdef DEBUG
                printf("Configuration mb: ADDRESS=%s, PORT=%d\n", config->address_mb, config->port_mb);
            #endif
            break;
        }
    }

    message_queue_parsing: for(int i=0; i<strlen(argv[2]); i++){
        if( argv[2][i] != ':'){
            config->address_mq[i] = argv[2][i];
        }
        else{
            config->address_mq[i] = '\0';
            char *port_val = argv[2];
            config->port_mq = atoi(port_val+i+1);
            #ifdef DEBUG
                printf("Configuration mq: ADDRESS=%s, PORT=%d\n", config->address_mq, config->port_mq);
            #endif
            break;
        }
    }
};

// Parses configuration with format host:port using command line arguments
void parse_arguments_as_server_config(server_config_t *config, int argc, char *argv[]){
    if(argc != 2){
        printf("Arguments invalid.");
        exit(0);
    }

    server_parsing: for(int i=0; i<strlen(argv[1]); i++){
        if( argv[1][i] != ':'){
            config->address[i] = argv[1][i];
        }
        else{
            config->address[i] = '\0';
            char *port_val = argv[1];
            config->port = atoi(port_val+i+1);
            #ifdef DEBUG
                printf("Configuration mb: ADDRESS=%s, PORT=%d\n", config->address, config->port);
            #endif
            break;
        }
    }
};

// Parses configuration with format host:port using command line arguments
void parse_arguments_as_config(server_config_t *config, int argc, char *argv[]){
    if(argc != 3){
        printf("Arguments invalid.");
        exit(0);
    }

    server_parsing: for(int i=0; i<strlen(argv[1]); i++){
        if( argv[1][i] != ':'){
            config->address[i] = argv[1][i];
        }
        else{
            config->address[i] = '\0';
            char *port_val = argv[1];
            config->port = atoi(port_val+i+1);
            #ifdef DEBUG
                printf("Configuration mb: ADDRESS=%s, PORT=%d\n", config->address, config->port);
            #endif
            break;
        }
    }

    pid_parsing: {
        config->pid = atoi(argv[2]);
    }
};


#endif