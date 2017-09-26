#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int main(int argc, char *argv[])
{

    int ssocket, csocket, port = atoi(argv[1]), queue = atoi(argv[2]), time_offset = atoi(argv[3]);
    printf("Starting server with the following configuration:\n");
    printf("\tPORT       %d\n", port);
    printf("\tMAX_QUEUE  %d\n", queue);
    printf("\tTIMEZONE   UTC %d\n", time_offset);

    struct sockaddr_in server;
    struct sockaddr_in client;

    assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);

    server.sin_family = AF_INET;
    server.sin_port = htons(port);
    server.sin_addr.s_addr = INADDR_ANY;
    bzero(&(server.sin_zero), 8);

    assert(bind(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);
    assert(listen(ssocket, queue) != -1);
    printf("Starting server...");
    while (1) {
        int sin_size = sizeof(struct sockaddr_in);
        assert((csocket = accept(ssocket, (struct sockaddr *)&client, &sin_size)) != -1);

        time_t current_time = time(NULL);
        char c_time_str[100];
        strcpy(c_time_str, ctime(&current_time));
        char result[100];
        strcpy(result, inet_ntoa(client.sin_addr));
        printf("[%s] Request recieved from %s\n", c_time_str ,result);

        current_time = time(NULL);
        char adj_time_str[100];
        current_time += + (60*60*time_offset);
        strcpy(adj_time_str, ctime(&current_time));
        assert(send(csocket, adj_time_str, strlen(adj_time_str), 0));

        current_time = time(NULL);
        strcpy(c_time_str, ctime(&current_time));
        printf("[%s] Sent %zu bytes to %s. Message: \"%s\"\n", c_time_str, strlen(adj_time_str) ,result, adj_time_str);
        assert(close(csocket)!= -1);
    }
    return 0;
}
