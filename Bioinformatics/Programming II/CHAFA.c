/*
 ============================================================================
 Name        : CHAFA.c
 Author      : Erik Andres Regla Torres
 Version     :
 Copyright   :
 Description : ejercicio 1: CHAFA!
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

typedef struct Lista {
	char origen[30];
	char destino[30];
	int horaArribo;
	int minutosArribo;
	int horaSalida;
	int minutosSalida;
	struct Lista *siguiente;
}Data_t;

Data_t* startNewNode(void);
void findRoute(Data_t*);
Data_t* addNewNode(Data_t*);
int menu(void);
void findTime1(Data_t*);
void findTime2(Data_t*);
void clearMemory(Data_t*);

int main(void) {
	Data_t *data_r = startNewNode();
	int selection=menu();
	while (selection!= 5) {
		if (selection == 1) {
			data_r = addNewNode(data_r);
		}
		if (selection == 2) {
			findRoute(data_r);
		}
		if (selection == 3) {
			findTime1(data_r);
		}
		if (selection == 4) {
			findTime2(data_r);
		}
		selection=menu();
	}
	clearMemory(data_r);
	return EXIT_SUCCESS;
}

int menu(void) {
	int choice = -1;
	do {
		printf("Menu:\n\n");
		printf("Por favor, elija entre las siguientes opciones:\n");
		printf("\n [1].- Crear nueva entrada.");
		printf("\n [2].- Confirmar existencia de un tramo");
		printf("\n [3].- Confirmar presencia de un tramo un un horario (Salida).");
		printf("\n [4].- Confirmar presencia de un tramo un un horario (Arribo).");
		printf("\n [5].- Salir.");
		printf("\n\n   Ingrese su opcion en formato numerico... ");
		scanf("%d", &choice);
		getc(stdin);
	} while (!((0<choice) && (choice < 6)));
	return choice;
}

Data_t* addNewNode(Data_t *source){
	Data_t *sourcePtr = source;

	while (source->siguiente != NULL){
		printf("\nexisten mas nodos!!\n");
		source = source->siguiente;
	}
	source->siguiente = (Data_t*)malloc(sizeof(Data_t));
	source = source->siguiente;
	source->siguiente = NULL;

	printf("\nCreando nuevo nodo...\n\nIngrese origen: ");
	fgets(source->origen,30,stdin);
	printf("Ingrese destino: ");
	fgets(source->destino,30,stdin);
	printf("Ingrese Hora de salida (HH:MM): ");
	scanf("%d:%d",&source->horaSalida,&source->minutosSalida);
	printf("Ingrese Hora de arribo (HH:MM): ");
	scanf("%d:%d",&source->horaArribo,&source->minutosArribo);
	getc(stdin);
	return sourcePtr;
}

void findRoute(Data_t *source){
	Data_t *sourcePtr = source;
	int found=0;
	char city[30];
	printf("Ingrese ciudad a buscar tramos realacionados: ");
	fgets(city,30,stdin);
	if((strcmp(city,sourcePtr->destino)==0)||(strcmp(city,sourcePtr->origen)==0)){
		printf("\nTramo encontrado!\n");
		printf("Origen: %s",sourcePtr->origen);
		printf("Destino: %s",sourcePtr->destino);
		printf("Hora salida: %d:%d\n",sourcePtr->horaSalida,sourcePtr->minutosSalida);
		printf("Hora arribo: %d:%d\n",sourcePtr->horaArribo,sourcePtr->minutosArribo);
	}
	sourcePtr = sourcePtr->siguiente;
	while((sourcePtr!= NULL)&&(found==0)){
		if((strcmp(city,sourcePtr->destino)==0)||(strcmp(city,sourcePtr->origen)==0)){
			printf("\nTramo encontrado!\n");
			printf("Origen: %s",sourcePtr->origen);
			printf("Destino: %s",sourcePtr->destino);
			printf("Hora salida: %d:%d\n",sourcePtr->horaSalida,sourcePtr->minutosSalida);
			printf("Hora arribo: %d:%d\n",sourcePtr->horaArribo,sourcePtr->minutosArribo);
		}
		sourcePtr = sourcePtr->siguiente;
	}
}

void findTime1(Data_t *source){
	Data_t *sourcePtr = source;
	int found=0, hora, minuto;
	printf("Ingrese hora de salida a buscar: ");
	scanf("%d:%d",&hora,&minuto);
	getc(stdin);
	if((hora==sourcePtr->horaSalida)&&(minuto==sourcePtr->minutosSalida)){
		printf("\nEncontrado tramo vigente!\n");
		printf("Origen: %s",sourcePtr->origen);
		printf("Destino: %s",sourcePtr->destino);
		printf("Hora salida: %d:%d\n",sourcePtr->horaSalida,sourcePtr->minutosSalida);
		printf("Hora arribo: %d:%d\n",sourcePtr->horaArribo,sourcePtr->minutosArribo);
	}
	sourcePtr = sourcePtr->siguiente;
	while((sourcePtr!= NULL)&&(found==0)){
		if((hora==sourcePtr->horaSalida)&&(minuto==sourcePtr->minutosSalida)){
			printf("\nEncontrado tramo vigente!\n");
			printf("Origen: %s",sourcePtr->origen);
			printf("Destino: %s",sourcePtr->destino);
			printf("Hora salida: %d:%d\n",sourcePtr->horaSalida,sourcePtr->minutosSalida);
			printf("Hora arribo: %d:%d\n",sourcePtr->horaArribo,sourcePtr->minutosArribo);
		}
		sourcePtr = sourcePtr->siguiente;
	}
}

void findTime2(Data_t *source){
	Data_t *sourcePtr = source;
	int found=0, hora, minuto;
	printf("Ingrese hora de arribo a buscar: ");
	scanf("%d:%d",&hora,&minuto);
	getc(stdin);
	if((hora==sourcePtr->horaArribo)&&(minuto==sourcePtr->minutosArribo)){
		printf("\nEncontrado tramo vigente!\n");
		printf("Origen: %s",sourcePtr->origen);
		printf("Destino: %s",sourcePtr->destino);
		printf("Hora salida: %d:%d\n",sourcePtr->horaSalida,sourcePtr->minutosSalida);
		printf("Hora arribo: %d:%d\n",sourcePtr->horaArribo,sourcePtr->minutosArribo);
	}
	sourcePtr = sourcePtr->siguiente;
	while((sourcePtr!= NULL)&&(found==0)){
		if((hora==sourcePtr->horaArribo)&&(minuto==sourcePtr->minutosArribo)){
			printf("\nEncontrado tramo vigente!\n");
			printf("Origen: %s",sourcePtr->origen);
			printf("Destino: %s",sourcePtr->destino);
			printf("Hora salida: %d:%d\n",sourcePtr->horaSalida,sourcePtr->minutosSalida);
			printf("Hora arribo: %d:%d\n",sourcePtr->horaArribo,sourcePtr->minutosArribo);
		}
		sourcePtr = sourcePtr->siguiente;
	}
}

Data_t* startNewNode(void){
	Data_t *node= (Data_t *)malloc(sizeof(Data_t));

	printf("\nCreando nuevo nodo...\n\nIngrese origen: ");
	fgets(node->origen,30,stdin);
	printf("Ingrese destino: ");
	fgets(node->destino,30,stdin);
	printf("Ingrese Hora de salida (HH:MM): ");
	scanf("%d:%d",&node->horaSalida,&node->minutosSalida);
	printf("Ingrese Hora de arribo (HH:MM): ");
	scanf("%d:%d",&node->horaArribo,&node->minutosArribo);

	node->siguiente = NULL;
	return node;
}

void clearMemory(Data_t *data){
	if(data->siguiente!=NULL) clearMemory(data->siguiente);
	free(data);
}
