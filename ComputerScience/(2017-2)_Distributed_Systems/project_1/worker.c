#include "config.c"
#include "operations.c"
#include <time.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>

#define MAX_STRING_LENGTH 1024
#define DEBUG

// global configuration
server_config_t config;

int main(int argc, char *argv[]){
    srand(time(NULL));
    // This hack allows to generate quasi-unique pids.
    // pid = (char)getpid();
    // parse_arguments_as_client_config(&config, argc, argv);

    parse_arguments_as_config(&config, argc, argv);
    printf("PID=%d, Short_PID=%d", getpid(), config.pid);
    
    
    while(1){
        char buf[1024];
        int buf_len = 1024;

        //get and delete!
        int uuid = mq_get(config.pid, buf, &buf_len, &config);
        
        if(uuid > 0){
            mq_delete(config.pid, uuid, &config);

            encode: if(buf_len > 0){
                printf("Recieved message from pid=%d: %s\n", config.pid, buf);
                rot13(buf);
                mb_put(config.pid, buf, buf_len, config.pid, &config);
                printf("Encoded message for pid=%d: %s\n", config.pid, buf);
            }
        }
        int sleep_time = rand() % 5 + 1;
        sleep(sleep_time);
        printf("Waiting...\n");
    }

    return 0;
}