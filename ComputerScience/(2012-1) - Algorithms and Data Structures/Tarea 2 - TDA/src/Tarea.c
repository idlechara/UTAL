#include <stdio.h>
#include <stdlib.h>
#include "TDA/ListaEnzlazada.h"
#include <stdbool.h>

Pelicula_t *shouka;
int troll;

void ingresarPelicula() {
	int codigo = 0;
	printf("Codigo, ");
	codigo = leerEntero(0, 9999);
	if (codeExists(shouka, codigo)) {
		printf(
				"La película ya existe. Incrementando en 1 el stock disponible.");
		shouka = addToCode(shouka, codigo, &troll);
	} else {
		char tit[30] = "";
		char act[30] = "";
		int yea = 0;
		int typ = 0, val = 0;
		printf("Titulo: ");
		fgets(tit, sizeof(tit), stdin);
		printf("Actor Principal: ");
		fgets(act, sizeof(act), stdin);
		printf("Año: ");
		yea = leerEntero(0, 999999);
		printf("Tipo: ");
		typ = leerEntero(ESTRENO, ECONOMICA);
		printf("Valor: ");
		val = leerEntero(0, 999999);
		int len = 0;
		shouka = addNewNode(shouka, newNode(tit,act,codigo,yea, typ, val, 1, 0));
	}
}
void eliminarPelicula() {
	int codigo = 0;
	printf("Codigo, ");
	codigo = leerEntero(0, 9999);
	if (codeExists(shouka, codigo)) {
		printf("Pelicula encontrada, borrando...");
		shouka = removeToCode(shouka, codigo, &troll);
	} else {
		printf("Pelicula no encontrada.");
	}
}

void imprimeGanaciaTotal() {
	int ganacia = 0;
	ganacia = earningsAllNodes(shouka);
	printf("Ganancia total de arriendos: %d", ganacia);
}

void ingresaArriendo() {
	int codigo = 0;
	printf("Codigo, ");
	codigo = leerEntero(0, 9999);
	if (codeExists(shouka, codigo)) {
		shouka = lendMovie(shouka, codigo, troll);
		if (!troll) {
			printf("Ingresando arriendo...");
		} else {
			printf("Maxima cantidad sobrepasada, no se puede arrendar...");
		}
	} else {
		printf("Pelicula no encontrada.");
	}
}

void ingresaDevolucion() {
	int codigo = 0;
	printf("Codigo, ");
	codigo = leerEntero(0, 9999);
	if (codeExists(shouka, codigo)) {
		shouka = lendMovie(shouka, codigo, troll);
		if (!troll) {
			printf("Ingresando devolucion...");
		} else {
			printf("Minima cantidad sobrepasada, no se puede devolver...");
		}
	} else {
		printf("Pelicula no encontrada.");
	}
}

void imprimeListadoInformacion() {
	shouka = printAllNodes(shouka);
}

int leerEntero(int min, int max) {
	int choice = 0;

	do {
		printf("Ingrese valor... ");
		scanf("%d", &choice);
		getc(stdin);
	} while (!((min <= choice) && (choice <= max)));
	return choice;
}

int menu(void) {
	int choice = -1;
	do {
		printf("\n [1].- Ingresar una pelicula.");
		printf("\n [2].- Eliminar una pelicula.");
		printf("\n [3].- Ganancia de todos los arriendos actuales.");
		printf("\n [4].- Ingresar un arriendo.");
		printf("\n [5].- Ingresar una devolución.");
		printf("\n [6].- Listado de la información de todas las películas.");
		printf(
				"\n [7].- Listado de los títulos ordenados de las películas que no están arrendadas.");
		printf("\n [8].- Salir.\n ");
		choice = leerEntero(1, 8);
	} while (!((0 < choice) && (choice < 9)));
	return choice;
}
int main(void) {
	Pelicula_t *item = NULL;
	item = addNewNode(item, newNode("Nueva peli2", "Yo", 1, 1, 1, 1, 1, 1));
	item = addNewNode(item, newNode("Nueva peli3", "Yo", 2, 2, 2, 2, 2, 2));
	item = addNewNode(item, newNode("Nueva peli4", "Yo", 3, 3, 3, 3, 3, 3));
	item = addNewNode(item, newNode("Nueva peli5", "Yo", 4, 4, 4, 4, 4, 4));
	item = printAllNodes(item);
	shouka = item;
//	puts("-----\n");
//	item = deleteNodeByCode(item, 3);
//	item = printAllNodes(item);
//	puts("-----\n");
	int seleccion = 0;
	do {
		seleccion = menu();
		switch (seleccion) {
		case 1:
			ingresarPelicula();
			break;
		case 2:
			eliminarPelicula();
			break;
		case 3:
			imprimeGanaciaTotal();
			break;
		case 4:
			ingresaArriendo();
			break;
		case 5:
			ingresaDevolucion();
			break;
		case 6:
			imprimeListadoInformacion();
			break;
		default:
			break;
		}
		printf("\nPresione [ENTER] para continuar... ");
		getc(stdin);
	} while (seleccion != 8);

	return EXIT_SUCCESS;
}
