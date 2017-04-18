//============================================================================
// Name        : Tarea.cpp
// Author      : Erik Andres Regla Torres
// Version     :
// Copyright   : A-Drops
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <stdlib.h>
#include <sys/time.h>
#include <unistd.h>
#include "Tools/Options.h"
#include "math.h"
using namespace std;

/*
 * Retorna el primo anterior al numero solicitado.
 * Precondiciones: 	v es un arreglo definido
 * 					n es un numero no mayor a el tamaño de v
 * Postcondiciones:	Una vez encontrado el numero primo
 * 					menor a n, es retornado. Si no es encontrado,
 * 					retorna 0.
 * Invariantes:		v[i] es un numero primo, menor que n
 */
int getPrevPrimeFrom(bool v[], int n) {
	for (int i = n - 1; i >= 0; i--) {
		if (v[i]) {
//			cout << "solicitado: " << n << " ,entregado: " << i << endl;
			return i;
		}
	}
	return 0;
}

int getPrevEqualPrimeFrom(bool v[], int n) {
	for (int i = n; i >= 0; i--) {
		if (v[i]) {
//			cout << "solicitado: " << n << " ,entregado: " << i << endl;
			return i;
		}
	}
	return 0;
}

/*
 * Retorna el primo siguente al numero solicitado.
 * Precondiciones: 	v es un arreglo definido
 * 					n es un numero no mayor a el tamaño de v
 * 					maxV es la dimension de v
 * Postcondiciones:	Una vez encontrado el numero primo
 * 					mayor a n, es retornado. Si no es encontrado,
 * 					retorna 0.
 * Invariantes:		v[i] es un numero primo, mayor que n
 */
int getNextPrimeFrom(bool v[], int maxV, int n) {
	for (int i = n + 1; i <= maxV; i++) {
		if (v[i])
			return i;
	}
	return 0;
}
int getNextEqualPrimeFrom(bool v[], int maxV, int n) {
	for (int i = n; i <= maxV; i++) {
		if (v[i])
			return i;
	}
	return 0;
}

bool _conjetura1(bool t[], int sizeV, int target) {
//	cout << "Iniciando ejecucion de conjetura sobre n="<< target  << endl;
	int lesserPrime = getPrevEqualPrimeFrom(t, target);
	int _lesserPrime = getNextPrimeFrom(t, sizeV, 1);
	while (lesserPrime >= target / 2) {
		if (lesserPrime == target)
			return true;
//		cout << "lesserPrime >= target/2: " << lesserPrime << " >= " << target/2 << endl;
		_lesserPrime = getNextEqualPrimeFrom(t, lesserPrime,
				(target - lesserPrime));
//		cout << "_lesserPrime <= target/2: " << _lesserPrime << " <= " << target/2 << endl;
		while (_lesserPrime <= target / 2) {
//			cout << "inicio ciclo inferior";
//			sleep(1);
//			cout << "Verificando: " << target << " = " << lesserPrime << " + "
//					<< _lesserPrime << endl;
			if (lesserPrime + _lesserPrime == target)
				return true;
			if (lesserPrime + _lesserPrime > target)
				break;
			_lesserPrime = getNextPrimeFrom(t, lesserPrime, _lesserPrime);
		}
		lesserPrime = getPrevPrimeFrom(t, lesserPrime);
//		cout << "Fin ciclo primario---_lesserPrime: " << _lesserPrime  << " lesserPrime: " << lesserPrime << endl;
	}
//	cout << "Ejecucion de _conjetura terminada" << endl;
	return false;
}

bool _conjetura2(bool t[], int sizeV, int target) {
	int value1 = getPrevEqualPrimeFrom(t, target), value2 = 0, value3 = 0;

	while (value1 >= target / 3) {
//		cout << "Verificando: " << target << " = " << value1 << endl;
		if (value1 > target)
			break;
		if (value1 == target)
			return true;
		value2 = getPrevEqualPrimeFrom(t, target - value1);

		while (value2 <= 2 * (target / 3)) {
//			cout << "Verificando: " << target << " = " << value1 << " + "
//					<< value2 << endl;
			if (value1 + value2 > target)
				break;
			if (value1 + value2 == target)
				return true;
			value3 = getNextEqualPrimeFrom(t, value2,
					(target - value1 - value2));

			while (value3 < target / 3) {
//				cout << "Verificando: " << target << " = " << value1 << " + "
//						<< value2 << " + " << value3 << endl;
				if (value1 + value2 + value3 > target)
					break;
				if (value1 + value2 + value3 == target)
					return true;
				value3 = getNextPrimeFrom(t, value2, value3);
			}
			value2 = getNextPrimeFrom(t, sizeV, value2);
		}
		value1 = getPrevPrimeFrom(t, value1);
	}
	return false;
}

bool _conjetura3(bool t[], int sizeV, int target) {
	int value1 = getPrevEqualPrimeFrom(t, target), value2 = 0, value3 = 0,
			value4 = 0, value5 = 0;

	while (value1 >= target / 5) {
//		cout << "Verificando: " << target << " = " << value1 << endl;
		if (value1 > target)
			break;
		if (value1 == target)
			return true;
		value2 = getPrevEqualPrimeFrom(t, target - value1);

		while (value2 <= 4 * (target / 5)) {
//			cout << "Verificando: " << target << " = " << value1 << " + "
//					<< value2 << endl;
			if (value1 + value2 > target || (value2 == value1))
				break;
			if (value1 + value2 == target)
				return true;
			value3 = getPrevEqualPrimeFrom(t, (target - value1 - value2));

			while (value3 <= 3 * (target / 5)) {
//				cout << "Verificando: " << target << " = " << value1 << " + "
//						<< value2 << " + " << value3 << endl;
				if (value1 + value2 + value3 > target || (value3 == value1)
						|| (value2 == value3))
					break;
				if (value1 + value2 + value3 == target)
					return true;
				value4 = getPrevEqualPrimeFrom(t,
						(target - value1 - value2 - value3));

				while (value4 <= 2 * (target / 5)) {
//					cout << "Verificando: " << target << " = " << value1
//							<< " + " << value2 << " + " << value3 << " + "
//							<< value4 << endl;
					if ((value1 + value2 + value3 + value4 > target)
							|| (value3 == value4) || (value2 == value4)
							|| (value1 == value4))
						break;
					if (value1 + value2 + value3 + value4 == target)
						return true;
					value5 = getPrevPrimeFrom(t,
							(target - value1 - value2 - value3 - value4));

					while (value5 <= (target / 5)) {
//						cout << "Verificando: " << target << " = " << value1
//								<< " + " << value2 << " + " << value3 << " + " << value4 << " + " << value5 << endl;
						if ((value1 + value2 + value3 + value4 + value5 > target)
								|| (value3 == value5) || (value2 == value5)
								|| (value1 == value5) || (value4 == value5))
							break;
						if (value1 + value2 + value3 + value4 + value5
								== target)
							return true;
						value5 = getNextPrimeFrom(t, sizeV, value5);
					}
					value4 = getNextPrimeFrom(t, sizeV, value4);
				}
				value3 = getNextPrimeFrom(t, sizeV, value3);
			}
			value2 = getNextPrimeFrom(t, sizeV, value2);
		}
		value1 = getPrevPrimeFrom(t, value1);
	}
	return false;
}

/*
 * Verifica la conjetura par de Goldbach para cada numero par desde el 2.
 *
 * Precondiciones: 	v y t son arreglos definidos, donde t contiene el indice
 * 					de primos y v los numeros que lo son.
 * 					sizeV es el tamaño de t, target es un numero natural menor
 * 					que sizeV
 * Postcondiciones:	regresa true en caso que todos los numero probados, cumplan
 * 					con la conjetura.
 * Invariantes:		??
 *
 */
bool conjetura1(bool t[], int v[], int sizeV, int target) {
	bool accomplished = true;
	string numeros = "";
	for (int i = 2; i <= target; i += 2) {
		//sleep(1);
		if (!_conjetura1(t, sizeV, i)){
			accomplished = false;
			numeros += i + " ";
		}
	}
	if(accomplished) cout << "La conjetura par de Goldbach se cumple!!!";
	else cout << "La conjetura par de Goldbach no se cumple!!! Numeros que la fallan: " << numeros << endl;
	return true;
}

/*
 * Verifica la conjetura impar de Goldbach para cada numero par desde el 3.
 *
 * Precondiciones: 	v y t son arreglos definidos, donde t contiene el indice
 * 					de primos y v los numeros que lo son.
 * 					sizeV es el tamaño de t, target es un numero natural menor
 * 					que sizeV
 * Postcondiciones:	regresa true en caso que todos los numero probados, cumplan
 * 					con la conjetura.
 * Invariantes:		??
 *
 */
bool conjetura2(bool t[], int v[], int sizeV, int target) {
	bool accomplished = true;
	string numeros = "";
	for (int i = 5; i <= target; i += 2) {
		//sleep(1);
		if (!_conjetura2(t, sizeV, i)){
			accomplished = false;
			numeros += i + " ";
		}
	}
	if(accomplished) cout << "La conjetura impar de Goldbach se cumple!!!";
	else cout << "La conjetura impar de Goldbach no se cumple!!! Numeros que la fallan: " << numeros << endl;
	return true;
}


/*
 * Verifica la conjetura par de Goldbach para cada numero desde el 5
 *
 * Precondiciones: 	v y t son arreglos definidos, donde t contiene el indice
 * 					de primos y v los numeros que lo son.
 * 					sizeV es el tamaño de t, target es un numero natural menor
 * 					que sizeV
 * Postcondiciones:	regresa true en caso que todos los numero probados, cumplan
 * 					con la conjetura.
 * Invariantes:		??
 *
 */
bool conjetura3(bool t[], int v[], int sizeV, int target) {
	bool accomplished = true;
	string numeros = "";
	for (int i = 7; i <= target; i++) {
		//sleep(1);
		if (!_conjetura3(t, sizeV, i)){
			accomplished = false;
			numeros += i + " ";
		}
	}
	if(accomplished) cout << "La conjetura de los cinco primos se cumple!!!";
	else cout << "La conjetura de los cinco primos no se cumple!!! Numeros que la fallan: " << numeros << endl;
	return true;
}

/*
 * Algoritmo para generar la criba de erastotenes.
 * Precondiciones: 	v es un arreglo de tamaño n y esta definido
 * Postcondiciones:	v, con un mapa de los valores
 * Invariantes:		v[i] determina la primicidad de i
 *
 */
void criba(bool v[], int n) {
	v[0] = false;
	v[1] = false;
	n++;

	for (int i = 2; i <= n; ++i)
		v[i] = true;

	//for (int i = 2; i <= tam; ++i) {
	for (int i = 2; i * i <= n; ++i) {
		if (v[i]) {
			for (int h = 2; (i * h) < n; ++h) {
				v[i * h] = false;
			}
		}
	}
}

long getElapsedTime(timeval start, timeval end){
    long mtime, seconds, useconds;
    seconds  = end.tv_sec  - start.tv_sec;
    useconds = end.tv_usec - start.tv_usec;
    mtime = ((seconds) * 1000 + useconds/1000.0) + 0.5;
    return mtime;
}

int main(int argc, char **argv) {

//	struct timeval start, end;

    Options options(argc, argv);
//	cout << "Iniciando algoritmo..." << endl;
	int lenght = options.max;
	bool *input = (bool*) malloc(sizeof(bool) * (lenght + 1));
//	cout << "Inicializacion de tabla terminada..." << endl;
//	cout << "Iniciando criba de Erastotenes sobre input[n]..." << endl;

//	gettimeofday(&start, NULL);
	criba(input, lenght);
//	gettimeofday(&end, NULL);

//	cout << "PrimeGeneratorElapsedTime: " << getElapsedTime(start, end) << endl;

	//	cout << "Criba terminada, generacion de numeros primos completa." << endl;
	int numeros = 0;
	for (int i = 0; i < lenght; ++i) {
		if (input[i])
			numeros++;
	}
//	cout << "Imprimiendo y almacenando criba (" << numeros << " numeros): "
//			<< endl;

	int *results = (int*) malloc(sizeof(int) * (numeros));

	for (int i = 0, j = 0; i < lenght; i++) {
		if (input[i])
			results[j++] = i;
	}

	if (options.verbose) {
		for (int i = 0; i < numeros; ++i) {
			cout << results[i] << endl;
		}
	}

	switch (options.conjetura) {
	case 1:
		conjetura1(input, results, numeros, options.max);
		break;
	case 2:
		conjetura2(input, results, numeros, options.max);
		break;
	case 3:
		conjetura3(input, results, numeros, options.max);
		break;
	default:
		break;
	}
	free(results);

	cout << endl;

	free(input);

//	cout << "Tabla liberada." << endl;

	//cout << "Maximum value for int: " << numeric_limits<int>::max() << endl;
}
