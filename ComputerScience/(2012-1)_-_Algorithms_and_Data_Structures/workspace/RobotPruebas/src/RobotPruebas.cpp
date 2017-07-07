//============================================================================
// Name        : RobotPruebas.cpp
// Author      : Erik Andres Regla Torres
// Version     :
// Copyright   : Copialo y te pego! D:
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "stdlib.h"
#include "sstream"
#include "math.h"
#include "time.h"

using namespace std;

int system_(const char *command) {
	cout << command << endl;
	return system(command);
}

int main(int args, char **argv) {
//	cout << "Robot de pruebas...";
//
//	cout << "Copiando ejecutables...";
//	int returnValue = 0;
//	returnValue += system_("cp ../proyecto3_2w/Debug/proyecto3_2w p3_2w");
//	returnValue += system_("cp ../proyecto3_8w/Debug/proyecto3_8w p3_2w");
//
//	if(!returnValue == 0){
//		cout << "Error en la copia de archivos!";
//		return 1;
//	}
//	cout << "Error en la copia de archivos!" << endl;
//
//	cout << "Compilando generador... " ;
//	returnValue += system_("g++ randomGen.cpp -o gen");
//
//
//	if(!returnValue == 0){
//		cout << "Error en la compilacion del generador!" << endl;
//		return 1;
//	}
//
//	cout << "listo!" << endl;
//
//	int resultados2w[3][10];
//	int resultados8w[3][10];
//
//	stringstream out;
//
//	int size;
//	for(int i=0; i<3; i++){
//
//		//genera el archivo de pruebas con 10mb, 100mb, y 1000mb
//		size =  pow(2,10) * pow(10,i+1);
//
//		cout << "[" << size <<"]: ";
//		for(int j=0; j<10; j++){
//			srand(time(0));
//			out.str("");
//			out << "gen " << size << " b " <<rand() ;
//			system_(out.str().data());
//			out.str("");
//			out << "p3_2w " << j+1;
//			resultados2w[i][j] = system_(out.str().data());
//
//			system_("rm data.bin_sorted");
//
//			out.str("");
//			out << "p3_8w " << j+1;
//			resultados8w[i][j] = system_(out.str().data());
//
//
//			system_("rm data.bin_sorted");
//			system_("rm data.bin");
//		}
//
//	}
//
//	returnValue += system_("cd Debug/temp");
//	system_("pwd");
//
//
//	cout  << endl << endl << returnValue << endl;
//
//	cout << endl << "resultados 2w";
//	for(int i=0; i<3; i++){
//		size =  pow(2,10) * pow(10,i+1);
//		cout << "[" << size << "]: ";
//		for(int j=0; j<10; j++){
//			cout << resultados2w[i][j];
//		}
//	}
//
//	cout << endl << endl << "resultados 8w";
//	for(int i=0; i<3; i++){
//		size =  pow(2,10) * pow(10,i+1);
//		cout << "[" << size << "]: ";
//		for(int j=0; j<10; j++){
//			cout << resultados8w[i][j];
//		}
//	}
//
//	return 0;

	stringstream out;

	srand(time(0));
	out.str("");
	for (int j = 0; j < 3; j++) {
		cout << "generando semilla aleatoria... tamaño: " << pow(2, 20) * pow(10,j);
		out.str("");
		out << "./gen " << pow(2, 20) * pow(10,j) << " b " << rand();
		system(out.str().data());
		for (int i = 1; i <= 10; i++) {
			cout << "usando paginas de "<< i << " mega elementos";
			out.str("");
			out << "./p3_2w " << i;
			system(out.str().data());
			system("rm data.bin_sorted");


			out.str("");
			out << "./p3_8w " << i;
			system(out.str().data());
			system("rm data.bin_sorted");
			system("rm data.bin");
		}
	}

	return 0;
}
