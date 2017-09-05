#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <assert.h>
#include <string.h>
#include <pthread.h>

#define MAX_STRING_LENGTH 100

typedef struct {
    char alias[MAX_STRING_LENGTH];
    int port;
    struct hostent *hostname;
} server_list_t;

int get_port(server_list_t *server, size_t size, char *alias) {
    for (int i = 0; i < size; i++)
    {
        if (strcmp(server[i].alias, alias) == 0)
        {
            return server[i].port;
        }
    }
    return -1;
}
struct hostent *get_hostent(server_list_t *server, size_t size, char *alias) {
    for (int i = 0; i < size; i++)
    {
        if (strcmp(server[i].alias, alias) == 0)
        {
            return server[i].hostname;
        }
    }
    return NULL;
}
int get_server(server_list_t *server, size_t size, char *alias) {
    for (int i = 0; i < size; i++)
    {
        if (strcmp(server[i].alias, alias) == 0)
        {
            return i;
        }
    }
    return -1;
}

static size_t server_list_memory;
static server_list_t *server_list;
static size_t server_list_size;

void *ask_delegated(int idx, char *string) {
    server_list_t _parameters = server_list[idx];

    int ssocket, numbytes = 0;
    struct sockaddr_in server;
    char buf[MAX_STRING_LENGTH];
    assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
    server.sin_family = AF_INET;
    server.sin_port = htons(_parameters.port);
    server.sin_addr = *((struct in_addr *)_parameters.hostname->h_addr);
    bzero(&(server.sin_zero), 8);
    assert(connect(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);
    assert((numbytes = recv(ssocket, buf, MAX_STRING_LENGTH, 0)) != -1);
    buf[numbytes] = '\0';
    printf("Time recieved from [%s]: %s\n",_parameters.alias, buf);
    strcpy(string, buf);
    close(ssocket); /* cerramos ssocket =) */
    
    return NULL;
}

int main(int argc, char *argv[]) {

    server_list_memory = 10;
    server_list = malloc(sizeof(server_list_t) * server_list_memory);
    server_list_size = 0;

    read_block:
    {
        char alias[MAX_STRING_LENGTH], hostname[MAX_STRING_LENGTH];
        int port;
        printf("Loading configuration: \n", alias, hostname, port);
        for (int i = 0; EOF != scanf("%s %d %s\n", alias, &port, hostname); i++)
        {
            char *ptr = server_list[i].alias;
            strcpy(ptr, alias);
            char *string = malloc(sizeof(char) * MAX_STRING_LENGTH);
            strcpy(string, hostname);
            server_list[i].hostname = gethostbyname((const char *)string);
            assert(server_list[i].hostname != NULL);
            server_list[i].port = port;
            if (i == server_list_memory)
            {
                server_list_memory += 10;
                assert(realloc(server_list, sizeof(server_list_t) * (server_list_memory)) != NULL);
            }
            server_list_size++;
            for (int j = 0; j < server_list_size; j++)
            {
                printf("ALIAS: %s, HOSTNAME: %s, PORT: %d\n", server_list[j].alias, server_list[j].hostname->h_name, server_list[j].port);
            }
        }
    }


    // read_block_test:
    // {
    //     char alias[MAX_STRING_LENGTH], hostname[MAX_STRING_LENGTH];
    //     int port;
    //     printf("Loading configuration: \n", alias, hostname, port);
    //     int _ports[3] = {3004,3005,3006};
    //     char *_alias[] = {"TALCA", "PARIS", "LONDRES"};
    //     char *_hostname[] = {"localhost", "localhost", "localhost"};
    //     for (int i = 0; i<3; i++)
    //     {
    //         char *ptr = server_list[i].alias;
    //         strcpy(ptr, _alias[i]);
    //         char *string = malloc(sizeof(char) * MAX_STRING_LENGTH);
    //         strcpy(string, _hostname[i]);
    //         server_list[i].hostname = gethostbyname((const char *)string);
    //         assert(server_list[i].hostname != NULL);
    //         server_list[i].port = _ports[i];
    //         if (i == server_list_memory)
    //         {
    //             server_list_memory += 10;
    //             assert(realloc(server_list, sizeof(server_list_t) * (server_list_memory)) != NULL);
    //         }
    //         server_list_size++;
    //         for (int j = 0; j < server_list_size; j++)
    //         {
    //             printf("ALIAS: %s, HOSTNAME: %s, PORT: %d\n", server_list[j].alias, server_list[j].hostname->h_name, server_list[j].port);
    //         }
    //     }
    // }


    listener_block : {
        int ssocket, csocket, port = atoi(argv[1]), queue = atoi(argv[2]);
        // int ssocket, csocket, port = 3000, queue = 100;
        printf("Starting server with the following configuration:\n");
        printf("\tPORT       %d\n", port);
        printf("\tMAX_QUEUE  %d\n", queue);

        struct sockaddr_in server;
        struct sockaddr_in client;
    
        int numbytes = 0;
        char buf[MAX_STRING_LENGTH];

        assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
    
        server.sin_family = AF_INET;
        server.sin_port = htons(port);
        server.sin_addr.s_addr = INADDR_ANY;
        bzero(&(server.sin_zero), 8);
    

        assert(bind(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);
        assert(listen(ssocket, queue) != -1);
        printf("Starting server...");
        while (1)
        {
            int sin_size = sizeof(struct sockaddr_in);
            printf("hiss");
            assert((csocket = accept(ssocket, (struct sockaddr *)&client, &sin_size)) != -1);
            char result[100];

            time_t current_time = time(NULL);
            char c_time_str[100];
            strcpy(c_time_str, ctime(&current_time));

            printf("AGGG");
            // strcpy(result, (char *)inet_ntoa(client.sin_addr));
            printf("TMRE");

            printf("[%s] Request recieved from %s\n",c_time_str, result);
            assert((numbytes = recv(csocket, buf, MAX_STRING_LENGTH, 0)) != -1);
            int server_idx = get_server(server_list, server_list_size, buf);
            printf("[%s] Request transfered to [%s] (%s:%d)\n",c_time_str,buf, server_list[server_idx].hostname->h_name, server_list[server_idx].port );
            char query_result[100];
            ask_delegated(server_idx, query_result);
            printf("[%s] Sent %zu bytes to %s. Message: \"%s\"\n", c_time_str, strlen(buf) ,server_list[server_idx].alias, query_result);
            assert(send(csocket, query_result, strlen(query_result), 0));
            assert(close(csocket)!= -1);
        }
    }
    
    return 0;
}