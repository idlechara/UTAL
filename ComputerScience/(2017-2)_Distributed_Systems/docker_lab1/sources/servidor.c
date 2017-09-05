#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <netdb.h>

#define BUFFER_SIZE 100
char reply_message[] = "Recibido\n";

int main(int argc, char *argv[]) {
   int conexion_servidor, conexion_cliente, puerto;
   struct sockaddr_in servidor, cliente;
   socklen_t longc; // Longitud de la estructura
   char buffer[BUFFER_SIZE]; // Mensaje recibido

   if (argc<2) {
      printf("%s <puerto>\n", argv[0]);
      return EXIT_FAILURE;
   }

   conexion_servidor = socket(AF_INET, SOCK_STREAM, 0);

   puerto = atoi(argv[1]);
   bzero((char *)&servidor, sizeof(servidor)); //llena la estructura de 0's
   servidor.sin_family = AF_INET;
   servidor.sin_port = htons(puerto);
   servidor.sin_addr.s_addr = INADDR_ANY; // dirección local
   if (bind(conexion_servidor, (struct sockaddr *)&servidor, sizeof(servidor)) < 0) {
      printf("Error al asociar el puerto a la conexion\n");
      close(conexion_servidor);
      return EXIT_FAILURE;
   }

   listen(conexion_servidor, 3);
   printf("Escuchando en el puerto %d\n", ntohs(servidor.sin_port));

   longc = sizeof(cliente);
   conexion_cliente = accept(conexion_servidor, (struct sockaddr *)&cliente, &longc);
   if (conexion_cliente<0) {
      printf("Error al aceptar trafico\n");
      close(conexion_servidor);
      return EXIT_FAILURE;
   }

   printf("Conectando con %s:%d\n", inet_ntoa(cliente.sin_addr),htons(cliente.sin_port));
   if (recv(conexion_cliente, buffer, BUFFER_SIZE, 0) < 0) {
      printf("Error al recibir los datos\n");
      close(conexion_servidor);
      return EXIT_FAILURE;
   }
   else {
      printf("Mensaje recibido: %s\n", buffer);
      bzero((char *)&buffer, sizeof(buffer));
      send(conexion_cliente, reply_message, strlen(reply_message), 0);
   }
   
   close(conexion_servidor);
   return EXIT_SUCCESS;
}
