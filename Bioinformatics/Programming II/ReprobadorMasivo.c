/*
 ============================================================================
 Name        : ReprobadorMasivo.c
 Author      : Erik Andres Regla Torres
 Version     :
 Copyright   :
 Description : ejercicio 2: REPRODADO!
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

typedef struct Alumno {
	int coorelativo;
	char *nombre;
} Alumno_t;

typedef struct Data {
	int *numbers;
	char **names;
	int howMany;
} Data_t;

int getLines(FILE*);
Data_t getData(FILE*);
Alumno_t newUser(FILE*, Data_t);
int menu(void);
int findNumber(int,int*,int);
char* findName(int, int*, int, Data_t);
void addStudent(Alumno_t, Data_t, FILE*);

int main(void) {
	FILE *students_file;
	students_file = fopen("/home/natsuko/alumnos.txt", "r");
	FILE *doomed_file;
	doomed_file = fopen("/home/natsuko/reprobados.txt", "r+");
	rewind(students_file);
	rewind(doomed_file);

	Data_t data = getData(students_file);
	fclose(students_file);
	Data_t data_r;
	int selection=menu();
	while (selection!= 3) {
		fclose(doomed_file);
		doomed_file = fopen("/home/natsuko/reprobados.txt", "r+");
		data_r = getData(doomed_file);
		if (selection == 1) {
			int i;
			//aca se extrae la lista de reprobados.
			printf("Lista de reprobados...\n\n");
			for(i=0;i < data_r.howMany-1; i++){
				printf("%d\t%s\n", data_r.numbers[i], data_r.names[i]);
			}
		}
		if (selection == 2) {
			//aca se debiera de comenzar a aplicar la reprobacion masiva!!!!
			Alumno_t user = newUser(students_file, data);
			char respuesta[100];
			printf("Logeado como %s.\n", user.nombre);
			printf("Si no respondes sabiamente, seras reprobado... jojoi!!!...\n");
			printf("\n... ¿Cual es el nivel maximo que se puede llegar con un personaje en cualquier\n");
			printf("\n... edicion del juego desarrollado por Square-Enix, Final Fantasy?... ");
			fgets(respuesta,100,stdin);
			fgets(respuesta,100,stdin);
			if(strcmp(respuesta,"99\n")!=0){
				printf("\n\nFelicidades... ud ha ");
				printf("REPROBADO!!!! jojoi!");
				addStudent(user, data_r, doomed_file);
			}
			else printf("Te salvaste... por ahora.\n\n\n");

		}
		selection=menu();
	}
	fclose(doomed_file);
	return EXIT_SUCCESS;
}

int menu(void) {
	int choice = -1;
	do {
		printf("Menu:\n\n");
		printf("Por favor, elija entre las siguientes opciones:\n");
		printf("\n [1].- Ver la lista de reprobados...");
		printf("\n [2].- Comenzar a Reprobar a los nubs!!!!");
		printf("\n [3].- Salir.");
		printf("\n\n   Ingrese su opcion en formato numerico... ");
		scanf("%d", &choice);
	} while (!((0<choice) && (choice < 4)));
	printf("retorna!!");
	return choice;
}

Alumno_t newUser(FILE *source, Data_t data) {
	int number, exists = 0;
	Alumno_t user;
	printf("\nIdenticicacion:\n\n");
	printf("Por favor, ingrese su numero coorelativo... ");
	scanf("%d", &number); /**falta por terminar*/
	exists = findNumber(number, data.numbers, data.howMany);
	while(exists == 0){
		printf("Identicicacion:\n\n");
		printf("Por favor, ingrese su numero coorelativo... ");
		scanf("%d", &number); /**falta por terminar*/
		exists = findNumber(number, data.numbers, data.howMany);
	}

	user.coorelativo = number;
	user.nombre = findName(number, data.numbers, data.howMany,data);
	return user;
}

Data_t getData(FILE *source) {
	Data_t data;
	int i, iterations = getLines(source);
	data.numbers = (int *) malloc(sizeof(int) * iterations);
	data.names = (char **) malloc(sizeof(char*) * iterations);
	for (i = 0; i < iterations; i++) {
		data.names[i] = (char *) malloc(sizeof(char) * 1000);
	}
	for (i = 0; i < iterations; i++) {
		fscanf(source, "%d", &data.numbers[i]);
		fgets(data.names[i], 1000, source);
	}
	data.howMany = iterations;
	rewind(source);
	return data;
}

int getLines(FILE *source) {
	int n = 0;
	char string[1000] = "";
	fgets(string, 1000, source);
	while (!feof(source)) {
		fgets(string, 1000, source);
		n++;
	}
	rewind(source);
	return (n);
}

int findNumber(int toFind, int *target, int lenght) {
	int i;
	for (i = 0; i < lenght; i++) {
		if (toFind == target[i]){
			return 1;
		}
	}
	return 0;
}

char* findName(int toFind, int *target, int lenght, Data_t data) {
	int i;
	for (i = 0; i < lenght; i++) {
		if (toFind == target[i])
			return data.names[i];
	}
	return NULL;
}

void addStudent(Alumno_t user, Data_t data, FILE *target){
	if(findNumber(user.coorelativo,data.numbers,data.howMany)==0){
		fseek(target, 0L, SEEK_END);
		fprintf(target,"%d%s",user.coorelativo,user.nombre);
	}
	rewind(target);
}

void clearMemory(Data_t *data){
	int i;
	for(i=data->howMany-1; 0<=i;i--){
		free(data->names[i]);
	}
	free(data->names);
	free(data->numbers);
	free(data);
}
