#define DEBUG

#include "config.c"
#include <assert.h>

#define MAX_STRING_LENGTH 100
server_config_t config;
char pid;

#include <pthread.h>

typedef struct {
    char pid;
    pthread_t thread;
    pthread_mutex_t lock;
} mailbox_response_thread_t;

typedef struct {
    mailbox_response_thread_t *threads;
    size_t size;
    size_t capacity;
} thread_pool_t;

void init_thread_pool(thread_pool_t *pool, size_t capacity){
    pool->capacity = capacity;
    pool->size = 0;
    pool->threads = malloc(sizeof(mailbox_response_thread_t) * capacity);
}

void* mailbox_response_server(void *args){
    int ssocket, csocket, queue = 100;
    struct sockaddr_in server;
    struct sockaddr_in client;
    assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
    server.sin_family = AF_INET;
    server.sin_port = htons(config.port+1);
    server.sin_addr.s_addr = INADDR_ANY;
    bzero(&(server.sin_zero), 8);
    assert(bind(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);
    assert(listen(ssocket, queue) < 0);
    while (1) {
        int sin_size = sizeof(struct sockaddr_in), numbytes = 4;
        assert((csocket = accept(ssocket, (struct sockaddr *)&client, &sin_size)) < 0);

        // assert(send(csocket, in_str, strlen(in_str), 0));
        printf("accepted! %s", in_str);

        assert((numbytes = recv(csocket, buf, MAX_STRING_LENGTH, 0)) < 0);
        printf("Recieved: %s", buf);

        assert(close(csocket)< 0);
    }
    return NULL;
}


void* queue_response_server(void *args){
    
    
    return NULL;
}


int main(int argc, char *argv[]){
    parse_arguments_as_server_config(&config, argc, argv);
    
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
    assert(listen(ssocket, queue) < 0);
    printf("Starting server...\n");

    while (1!=0) {
        int sin_size = sizeof(struct sockaddr_in), numbytes = 4;
        
        char in_str[100] = "ASDASD";
        char buf[100];

        printf("Accepting...");
        assert((csocket = accept(ssocket, (struct sockaddr *)&client, &sin_size)) < 0);

        // assert(send(csocket, in_str, strlen(in_str), 0));
        printf("accepted! %s", in_str);

        assert((numbytes = recv(csocket, buf, MAX_STRING_LENGTH, 0)) < 0);
        printf("Recieved: %s", buf);

        assert(close(csocket)< 0);
    }

    // while(1){
    //     char query[MAX_STRING_LENGTH];
    //     scanf("%s\n", query);

    //     query_loop: {
    //         int ssocket, numbytes = 0;
    //         struct sockaddr_in server;
    //         char buf[MAX_STRING_LENGTH];
    //         assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
    //         struct hostent *he = gethostbyname(address);
    //         server.sin_family = AF_INET;
    //         server.sin_port = htons(port);
    //         server.sin_addr = *((struct in_addr *)he->h_addr);
    //         bzero(&(server.sin_zero), 8);
    //         assert(connect(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);
    //         assert(send(ssocket, query, strlen(query), 0));
    //         assert((numbytes = recv(ssocket, buf, MAX_STRING_LENGTH, 0)) != -1);
    //         buf[numbytes] = '\0';
    //         printf("Time recieved from [%s]: %s\n",query, buf);
    //         close(ssocket); /* cerramos ssocket =) */
    //     }
    // }
    return 0;
}