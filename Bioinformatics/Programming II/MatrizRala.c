/*
 ============================================================================
 Name        : MatrizRala.c
 Author      : Erik Andres Regla Torres
 Version     :
 Copyright   :
 Description : ejercicio 3 :matriz rala
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

const int TRUE = 1;
const int FALSE = 0;

typedef int boolean;

typedef struct Matriz{
	int **data;
	int sizeX;
	int sizeY;
	int numeroChanta;
	int uselessData;
	int maxLenghtX;
	int maxLenghtY;
	boolean isRala;
}Matrix_t;

Matrix_t recibirMatriz(void);
void findRala(Matrix_t *);
Matrix_t createRala(Matrix_t);
void saveData(Matrix_t);
void freeData(Matrix_t);

int main(void) {
	Matrix_t matriz = recibirMatriz();
	Matrix_t rala = createRala(matriz);
	saveData(rala);
	freeData(matriz);
	freeData(rala);
	return EXIT_SUCCESS;
}

Matrix_t recibirMatriz(){
	//se reciben parametros
	int i, j, valor;
	Matrix_t matriz;
	printf("Ingrese dimension X: ");
	scanf("%d",&matriz.sizeX);
	printf("Ingrese dimension Y: ");
	scanf("%d",&matriz.sizeY);
	matriz.data = (int **)malloc(sizeof(int *)*matriz.sizeX);
	for(i=0;i<matriz.sizeX;i++){
		matriz.data[i]=(int *)malloc(sizeof(int)*matriz.sizeY);
	}
	for(i=0;i<matriz.sizeX;i++){
		for(j=0;j<matriz.sizeY;j++){
			scanf("%d", &valor);
			matriz.data[i][j] = valor;
		}
	}
	findRala(&matriz);
	matriz.isRala = FALSE;
	return matriz;
}

void findRala(Matrix_t *matrix){
	int i, j, zero = 0;
	for(i=0 ; i < matrix->sizeX; i++){
		for(j=0 ; j<matrix->sizeY; j++){
			if(matrix->data[i][j]==0) zero++;
		}
	}
	matrix->uselessData = zero;
	matrix->numeroChanta = 0;
}

Matrix_t createRala(Matrix_t matrix){
	int i,j,x;
	Matrix_t rala;
	rala.sizeX = (matrix.sizeX*matrix.sizeY) - matrix.uselessData;
	rala.sizeY = 3;
	rala.data = (int **)malloc(sizeof(int *)*rala.sizeX);
	for(i=0;i<rala.sizeX;i++){
		rala.data[i]=(int *)malloc(sizeof(int)*rala.sizeY);
	}
	x=0;
	for(i=0; i<matrix.sizeX; i++){
		for(j=0 ; j<matrix.sizeY; j++){
			if(matrix.data[i][j]!= matrix.numeroChanta){
				rala.data[x][0] = i;
				rala.data[x][1] = j;
				rala.data[x][2] = matrix.data[i][j];
				x++;
			}
		}
	}
	rala.maxLenghtX = matrix.sizeX;
	rala.maxLenghtY = matrix.sizeY;
	rala.isRala = TRUE;
	return rala;
}

void saveData(Matrix_t rala){
	char path[10000];
	printf("Ingrese ruta de salida: ");
	fflush(stdin);
	getc(stdin);
	fgets(path, 10000, stdin);
	FILE *output;
	output = fopen("/home/natsuko/wea","w+");

	int i, j, step=0;
	for(i=0;i<rala.maxLenghtX;i++){
		for(j=0;j<rala.maxLenghtY;j++){
			if((rala.data[step][0]==i)&&(rala.data[step][1]==j)){
				fprintf(output,"%d",rala.data[step][2]);
				fflush(output);
				step++;
			}
			else fprintf(output," ");
		}
		fprintf(output,"\n");
	}
	fclose(output);
}

void freeData(Matrix_t matrix){
	int i;
	for(i=0; i<matrix.maxLenghtX;i++){
		free(matrix.data[i]);
	}
	free(matrix.data);
}
