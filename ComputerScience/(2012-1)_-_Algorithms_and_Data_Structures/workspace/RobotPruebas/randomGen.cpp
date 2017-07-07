/*
 *  randomGen.cpp
 *  sqh
 *
 *  Created by Rodrigo Paredes on 03-06-12.
 *
 *  Fast uniform pseudo-random generator. Ranges : [0, 2^30) or [0.0, 1.0)
 *  period at least 2^31, random bits: 32
 * 
 */

#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#ifndef SQH_RANDOM_CPP
#define SQH_RANDOM_CPP

using namespace std;

void help(string filename, unsigned int seed) {
  cout << endl
  << "Generador rapido de numeros pseudo-aleatorios con distribucion uniforme." << endl
  << "La generacion es sistematica, es decir, dada una semilla la serie es unica." << endl
  << "Rango: [0, 2^30). Periodo de al menos 2^31. 32 bits pseudo-aleatorios" << endl
  << endl << "Uso: " << filename << " n t [seed]" << endl
  << "n    = cantidad de elementos a generar" << endl
  << "t    = tipo de datos. Alternativas a para ascii y b para binario" << endl
  << "seed = parametro opcional. Es la semilla de generacion. Valor actual " << seed << endl
  << endl << "Formato de salida ascii:"
  << " genera archivo data.ascii, que contiene 1 numero por cada linea" << endl
  << endl << "Formato de salida binario:"
  << " genera archivo data.bin, que es una secuencia de enteros, 4 bytes por cada entero" << endl
  << endl << flush;  
}

int main (int argc, char * const argv[]) {
  // insert code here...
  unsigned int ran32State = 0x0accede0; // random generator seed
                                        // Chicos: otra opcion es 0xdeadbeef, o
                                        // cualquier otro numero
  /* static const int randMax = 0x3fffffff; // 2^30 - 1
     cout << "randMax = " << randMax <<endl;
   */
  
  if (argc == 1) {
    help(argv[0], ran32State);
    return 0;
  }
  
  int n = atoi(argv[1]);
  
  if (argc == 4) {
      // cambiando la semilla
    ran32State = atoi(argv[3]);
  }
  cerr << "seed = " << ran32State << endl;
  
  int auxBuff; // variable auxiliar para escrituras en los archivos
  char* type = argv[2];
  FILE *archi;    
  
  if (strcmp(type, "a") == 0) {
    cerr << "generando valores en ascii. 1 por linea" << endl;
    archi = fopen("data.ascii", "w");
    for (int i = 0 ; i < n; i++) {
      ran32State = 1664525 * ran32State + 1013904223;
      auxBuff = ran32State >> 2;
        //cout << auxBuff << endl;
      fprintf(archi, "%d\n", auxBuff);
    }
    cerr << "Archivo data.ascii generado.";
  }
  else if (strcmp(type, "b") == 0){
    cerr << "generando valores en binario. Secuencia de enteros de 4 bytes" << endl;
    archi = fopen("data.bin", "wb");
    for (int i = 0 ; i < n; i++) {
      ran32State = 1664525 * ran32State + 1013904223;
        //cout << ran32State << endl;
      auxBuff = ran32State >> 2;
      fwrite(&auxBuff, 1, sizeof(int), archi);
    }
    cerr << "Archivo data.bin generando.";
      // este codigo es para leer los datos del archivo data.bin en binario
      // fclose(archi);
      // archi = fopen("data.bin", "rb");
      // for (int i = 0 ; i < n; i++) {
      //   fread(&auxBuff, 1, sizeof(int), archi);
      //   cout << auxBuff << "\n";
      // }
  }
  cerr << " Contiene " << n << " enteros" << endl;
  fclose(archi);
  
  return 0;
}

#endif
