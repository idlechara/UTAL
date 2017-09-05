#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>

#define BUFFER_SIZE 100

int main(int argc, char **argv) {
   struct sockaddr_in cliente;
   struct hostent *servidor;
   int puerto, conexion;
   char buffer[BUFFER_SIZE];

   if (argc<2) {
      printf("<host> <puerto>\n");
      return EXIT_FAILURE;
   }

   servidor = gethostbyname(argv[1]);
   if (servidor == NULL) {
      printf("Host erróneo\n");
      return EXIT_FAILURE;
   }
   
   conexion = socket(AF_INET, SOCK_STREAM, 0);

   puerto=(atoi(argv[2]));
   bzero((char *)&cliente, sizeof((char *)&cliente));
   cliente.sin_family = AF_INET;
   cliente.sin_port = htons(puerto);
   bcopy((char *)servidor->h_addr, (char *)&cliente.sin_addr.s_addr, sizeof(servidor->h_length));

   if (connect(conexion,(struct sockaddr *)&cliente, sizeof(cliente)) < 0) {
      printf("Error conectando con el host\n");
      close(conexion);
      return EXIT_FAILURE;
   }
   printf("Conectado con %s:%d\n",inet_ntoa(cliente.sin_addr),htons(cliente.sin_port));

   printf("Escriba un mensaje: ");
   fgets(buffer, BUFFER_SIZE, stdin);
   send(conexion, buffer, BUFFER_SIZE, 0);
   bzero(buffer, BUFFER_SIZE);
   recv(conexion, buffer, BUFFER_SIZE, 0);
   printf("%s", buffer);

   close(conexion);
   return EXIT_SUCCESS;
}
