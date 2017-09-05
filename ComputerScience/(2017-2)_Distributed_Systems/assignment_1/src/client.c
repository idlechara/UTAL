#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <assert.h>
#include <string.h>
#include <pthread.h>

#define MAX_STRING_LENGTH 100


int main(int argc, char *argv[]){
    int port = atoi(argv[1]);
    char address[100];
    strcpy(address, argv[2]);
    while(1){
        char query[MAX_STRING_LENGTH];
        scanf("%s\n", query);

        query_loop: {
            int ssocket, numbytes = 0;
            struct sockaddr_in server;
            char buf[MAX_STRING_LENGTH];
            assert((ssocket = socket(AF_INET, SOCK_STREAM, 0)) != -1);
            struct hostent *he = gethostbyname(address);
            server.sin_family = AF_INET;
            server.sin_port = htons(port);
            server.sin_addr = *((struct in_addr *)he->h_addr);
            bzero(&(server.sin_zero), 8);
            assert(connect(ssocket, (struct sockaddr *)&server, sizeof(struct sockaddr)) != -1);
            assert(send(ssocket, query, strlen(query), 0));
            assert((numbytes = recv(ssocket, buf, MAX_STRING_LENGTH, 0)) != -1);
            buf[numbytes] = '\0';
            printf("Time recieved from [%s]: %s\n",query, buf);
            close(ssocket); /* cerramos ssocket =) */
        }
    }
    return 0;
}