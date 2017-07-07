/*
 * ListaEnzlazada.h
 *
 *  Created on: May 28, 2013
 *      Author: kynku
 */

#ifndef LISTAENZLAZADA_H_
#define LISTAENZLAZADA_H_

#include "malloc.h"
#include "stdbool.h"
#include "ListaEnzlazada.h"

#define TRUE 1
#define FALSE 0
#define ESTRENO 0
#define NORMAL 1
#define ECONOMICA 2

typedef struct Pelicula {
	char *titulo;
	char *actorPrincipal;
	int codigo;
	int ano;
	int tipo;
	int valorArriendo;
	int stock;
	int numeroEjemplaresArrendados;
	struct Pelicula *siguiente;
} Pelicula_t;

Pelicula_t* newNode(char *_titulo, char *_actorPrincipal, int _codigo, int _ano,
		int _tipo, int _valorArriendo, int _stock, int _numeroEjemplares) {
	Pelicula_t *node = (Pelicula_t *) malloc(sizeof(Pelicula_t));

	node->actorPrincipal = _actorPrincipal;
	node->titulo = _titulo;
	node->codigo = _codigo;
	node->ano = _ano;
	node->tipo = _tipo;
	node->valorArriendo = _valorArriendo;
	node->stock = _stock;
	node->numeroEjemplaresArrendados = _numeroEjemplares;
	node->siguiente = NULL;

	return node;
}

Pelicula_t* addNewNode(Pelicula_t *source, Pelicula_t *newNode) {
	if (source == NULL ) {
		source = newNode;
		return source;
	}

	Pelicula_t *sourcePtr = source;

	while (source->siguiente != NULL ) {
		if (source->siguiente != NULL )
			source = source->siguiente;
		else
			break;
	}
	source->siguiente = newNode;
	return sourcePtr;
}

Pelicula_t* deleteNodeByCode(Pelicula_t *source, int code) {
	if (source->codigo == code) {
		Pelicula_t *resultPtr = source->siguiente;
		free(source);
		return resultPtr;
	}

	Pelicula_t *sourcePtr = source;
	Pelicula_t *tempPtr = source;

	while (source->siguiente != NULL ) {
		if (source->siguiente->codigo == code) {
			tempPtr = source->siguiente->siguiente;
			free(source->siguiente);
			source->siguiente = tempPtr;
			return sourcePtr;
		}
		if (source->siguiente != NULL )
			source = source->siguiente;
		else
			break;
	}
	return sourcePtr;
}

Pelicula_t* lendMovie(Pelicula_t *source, int code, bool *error) {
	Pelicula_t *sourcePtr = source;
	error = false;
	while (source != NULL ) {
		if (source->codigo == code) {
			if (source->numeroEjemplaresArrendados >= source->stock) {
				error = true;
				return sourcePtr;
			}

			source->numeroEjemplaresArrendados++;
			return sourcePtr;
		}
		if (source->siguiente != NULL )
			source = source->siguiente;
		else
			break;
	}
	return sourcePtr;
}

Pelicula_t* retrieveMovie(Pelicula_t *source, int code, bool *error) {
	Pelicula_t *sourcePtr = source;
	error = false;
	while (source != NULL ) {
		if (source->codigo == code) {
			if (source->numeroEjemplaresArrendados == 0) {
				error = true;
				return sourcePtr;
			}

			source->numeroEjemplaresArrendados--;
			return sourcePtr;
		}
		if (source->siguiente != NULL )
			source = source->siguiente;
		else
			break;
	}
	return sourcePtr;
}

Pelicula_t* addToCode(Pelicula_t *source, int code, bool *error) {
	Pelicula_t *sourcePtr = source;
	error = false;
	while (source != NULL ) {
		if (source->codigo == code) {
			source->stock++;
			return sourcePtr;
		}
		if (source->siguiente != NULL )
			source = source->siguiente;
		else
			break;
	}
	error = true;
	return sourcePtr;
}

Pelicula_t* removeToCode(Pelicula_t *source, int code, bool *error) {
	Pelicula_t *sourcePtr = source;
	error = false;
	while (source != NULL ) {
		if (source->codigo == code) {

			if (source->numeroEjemplaresArrendados == source->stock) {
				error = true;
				return sourcePtr;
			}

			if (source->stock == 1) {
				return deleteNodeByCode(sourcePtr, source->codigo);
			}

			source->stock--;
			return sourcePtr;
		}
		if (source->siguiente != NULL )
			source = source->siguiente;
		else
			break;
	}
	return sourcePtr;
}

int earningsAllNodes(Pelicula_t *source) {
	Pelicula_t *sourcePtr = source;
	int earnings = 0;
	while (source != NULL ) {
		earnings +=
				(source->valorArriendo * source->numeroEjemplaresArrendados);
		if (source->siguiente != NULL )
			source = source->siguiente;
		else
			break;
	}
	source = sourcePtr;
	return earnings;
}

Pelicula_t* printAllNodes(Pelicula_t *source) {
	Pelicula_t *sourcePtr = source;
	while (source != NULL ) {
		printf(" --- %s\n", source->titulo);
		printf("Año: %d\n", source->ano);
		printf("Codigo: %d\n", source->codigo);
		printf("Actor principal: %s\n", source->actorPrincipal);
		printf("Año: %d\n --- \n", source->ano);
		printf("Stock: %d\n --- \n", source->stock);
		printf("Arrendados: %d\n --- \n", source->numeroEjemplaresArrendados);
		if (source->siguiente != NULL )
			source = source->siguiente;
		else
			break;
	}
	return sourcePtr;
}

Pelicula_t* printLendedNodes(Pelicula_t *source) {
	Pelicula_t *sourcePtr = source;
	while (source != NULL ) {
		if (source->numeroEjemplaresArrendados > 0) {
			printf("El nombre de esta pelicula es: %s \n", source->titulo);
		}
		if (source->siguiente != NULL )
			source = source->siguiente;
		else
			break;
	}
	return sourcePtr;
}

Pelicula_t* dataLenght(Pelicula_t *source, int *lenght) {
	Pelicula_t *sourcePtr = source;
	int len = 0;
	while (source != NULL ) {
		len++;
		if (source->siguiente != NULL )
			source = source->siguiente;
		else
			break;
	}
	lenght = len;
	return sourcePtr;
}

bool codeExists(Pelicula_t *source, int code) {
	Pelicula_t *sourcePtr = source;

	printf("buscando: %d\n",code);
	while (sourcePtr != NULL ) {
		printf("%d", sourcePtr->codigo);
		if (sourcePtr->codigo == code) {
			printf("encontrada!");
			return true;
		}
		if (sourcePtr->siguiente != NULL ){
			printf("->");
			sourcePtr = sourcePtr->siguiente;
		}
		else
			break;

	}
	return false;
}

#endif /* LISTAENZLAZADA_H_ */
