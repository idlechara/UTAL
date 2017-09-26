#include "config.c"

#define MAX_STRING_LENGTH 1024
#define DEBUG

// global configuration
client_config_t config;
char pid;

#include <pthread.h>
void* establish_read_channel(void *args){
    printf("Starting mailbox_thread\n");

    int ssocket, numbytes = 0;
    struct sockaddr_in server;
    
    assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
    struct hostent *he = gethostbyname(config.address_mq);
    server.sin_family = AF_INET;
    server.sin_port = htons(config.port_mq+1);
    server.sin_addr = *((struct in_addr *)he->h_addr);
    bzero(&(server.sin_zero), 8);
    assert(connect(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);
    
    char buf[1];
    buf[0] = pid;
    assert(send(ssocket, buf, 1, 0));
    printf("Connection established with mailbox. Now reading...\n");

    mailbox_read_loop: while(1){
        message_t message;
        assert((numbytes = recv(ssocket, &message, sizeof(message_t), 0)) != -1);
        printf("Mailbox recieved: Message=%s, Contents=%s");
    }

    close(ssocket); /* cerramos ssocket =) */
    return NULL;
}

int main(int argc, char *argv[]){
    
    // This hack allows to generate quasi-unique pids.
    pid = (char)getpid();

    printf("PID=%d, Short_PID=%d", getpid(), pid);
    parse_arguments_as_client_config(&config, argc, argv);

    pthread_t mailbox_thread;
    if(pthread_create(&mailbox_thread, NULL, establish_read_channel, NULL)) {    
        fprintf(stderr, "Error creating thread\n");
        return 1;
    }
    
    // while(1){
        char query[MAX_STRING_LENGTH];
        scanf("%s", query);

        query_loop: {
            int ssocket, numbytes = 0;
            struct sockaddr_in server;
            char buf[MAX_STRING_LENGTH];
            assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
            struct hostent *he = gethostbyname(config.address_mq);
            server.sin_family = AF_INET;
            server.sin_port = htons(config.port_mq);
            server.sin_addr = *((struct in_addr *)he->h_addr);
            bzero(&(server.sin_zero), 8);
            assert(connect(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);
            assert(send(ssocket, query, strlen(query), 0));

            assert((numbytes = recv(ssocket, buf, MAX_STRING_LENGTH, 0)) != -1);
            buf[numbytes] = '\0';
            printf("Time recieved from [%s]: %s\n",query, buf);
            close(ssocket); /* cerramos ssocket =) */
        }


    // }

    if(pthread_join(mailbox_thread, NULL)) {
        fprintf(stderr, "Error joining thread\n");
        return 2;
    }

    return 0;
}