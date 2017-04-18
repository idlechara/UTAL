/*
 * TDAListaPeliculas.h
 *
 *  Created on: May 28, 2013
 *      Author: kynku
 */

#ifndef TDALISTAPELICULAS_H_
#define TDALISTAPELICULAS_H_

#include "malloc.h"

#define FALSE 0
#define TRUE 1
#define ESTRENO 0
#define NORMAL 1
#define ECONOMICA 2

typedef struct Pelicula {
	char titulo[30];
	char actorPrincipal[30];
	int codigo;
	int ano;
	int tipo;
	int valorArriendo;
	int stock;
	int numeroEjemplares;
	struct Lista *siguiente;
}Pelicula_t;

Pelicula_t*	newNode(char _titulo[30], char _actorPrincipal[30], int _codigo,
					int _ano, int _tipo, int _valorArriendo,
					int _stock, int _numeroEjemplares){

	Pelicula_t *node= (Pelicula_t *)malloc(sizeof(Pelicula_t));
//	node->titulo = _titulo;
//	node->actorPrincipal = _actorPrincipal;
	node->codigo = _codigo;
	node->ano = _ano;
	node->tipo = _tipo;
	node->valorArriendo = _valorArriendo;
	node->stock = _stock;
	node->numeroEjemplares = _numeroEjemplares;
	node->siguiente = NULL;
	return node;
}

Pelicula_t* addNewNode(Pelicula_t *source, Pelicula_t *newNode){
	Pelicula_t *sourcePtr = source;

	while (source->siguiente != NULL){
		source = source->siguiente;
	}
	source->siguiente = newNode;
	return sourcePtr;
}

Pelicula_t*	searchByTitle(Pelicula_t* data, char* _titulo){
	Pelicula_t *sourcePtr = data;
	int endReached = FALSE;
	while (endReached != FALSE && sourcePtr->titulo != _titulo){
		endReached = endOfList(sourcePtr);
		sourcePtr = sourcePtr->siguiente;
	}

	if(!endReached){
		return sourcePtr;
	}
	else{
		return NULL;
	}
}

void deleteByCode(Pelicula_t* data, int _codigo){
	Pelicula_t *sourcePtr = data;
	int endReached = endOfList(sourcePtr->siguiente);
	while (endReached != FALSE && sourcePtr->siguiente->codigo != _codigo){
		endReached = endOfList(sourcePtr->siguiente);
		sourcePtr = sourcePtr->siguiente;
	}

	if(!endReached){

	}
	else{
	}
}


void	increaseStock(Pelicula_t* data, int value);
void	decreaseStock(Pelicula_t* data, int value);
void	lendStock(Pelicula_t* data, int value);
void	retrieveStock(Pelicula_t* data, int value);
int		sumEarnings(Pelicula_t* data);

int endOfList(Pelicula_t* data){
	return (data->siguiente == NULL)? TRUE : FALSE;
}

#endif /* TDALISTAPELICULAS_H_ */
