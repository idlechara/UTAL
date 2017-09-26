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
    printf("PID=%d, Short_PID=%d\n", getpid(), config.pid);
    
    
    while(1){
        char buf[1024];
        if(scanf("%s", buf) <= 0){
            break;
        };
        int buf_len = strlen(buf);

        mq_put(config.pid, buf, buf_len, &config);

        bool recieved = false;
        memset(buf, '\0', 1024);
        buf_len = 0;

        while(recieved == false){
            mb_get(config.pid, buf, &buf_len, config.pid, &config);   // There was no spec about how to select queues
            if(buf_len > 0){
                recieved = true;
            }
            else{
                int sleep_time = rand() % 5 + 1;
                sleep(sleep_time);
                printf("Waiting...\n");
            }
        }
        printf("Message encoded: %s\n", buf);
    }

    return 0;
}