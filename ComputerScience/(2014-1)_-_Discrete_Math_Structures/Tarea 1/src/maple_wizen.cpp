/*
    Maple Wizen - An implementation of a "Recursive Fibonacci"
    Copyright (C) Erik Regla (eregla09@alumnos.utalca.cl), Alejandro Naranjo (anaranjo11@alumnos.utalca.cl)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
	MapleWizen: Programa que implementa la resolución de la tarea 1 de ECD

	Origen del nombre: https://www.youtube.com/watch?v=4QRXR8xwlAU

	Dependencias:
		libgmp3-dev
*/

/*
	NOTA: Para el caso del fibonacci multiplicativo en versión expresiva (?)
	se dejó que vuelva a calcular el número nuevamente. La idea es que el algoritmo
	ejecute una y otra vez sin tener ventaja alguna de la ejecución anterior, de modo
	de obtener datos mas realistas.

	NOTA2: Se usó debido a programación entera el cálculo de fibonacci usando la recursión
	conocida, evaluar la fórmula no es posible en una máquina debido al "problema" mencionado
	anteriormente... Al menos, no sin una taza de error que no estamos dispuestos a correr.
*/

#include <gmpxx.h>
#include <iostream>

//calcula el fibonacci de un numero
void fibonacci(mpz_class &target, unsigned int n){
	mpz_class a=0, b=1, bubble = 0;
	bubble = a + b;
	for (unsigned int i = 3; i < n; ++i){
		a = b;
		b = bubble; 
		bubble = a +b;
	}
	target = bubble;
}

//calcula el "fibonacci multiplicativo" de un numero
void fibo_multiplicativo(mpz_class &target, unsigned int value){
	mpz_class base = 2, power;
	fibonacci(power, value);
	mpz_pow_ui(	target.get_mpz_t(), 			//Ejecuta operación
				base.get_mpz_t(), 
				power.get_ui());
}

//idem, pero en versión recursiva
mpz_class fibo_recur(mpz_class target){
	if(target == 1)								//CASO BASE 1
		return 1;
	else if (target == 2)						//CASO BASE 2
		return 2;
	return mpz_class( 	fibo_recur(target-1) *
	  					fibo_recur(target-2) );	//PASO RECURSIVO
}

int main (void)
{
	mpz_class resultado_formula;				//Resultado fórmula
	for(long int i = 50; i <= 50; i++){
		#ifdef EXPRESSION
			fibo_multiplicativo(resultado_formula, i);
		#elif RECURSSIVE
			mpz_class recursion = i;
			resultado_formula = fibo_recur(recursion);
		#endif
		std::cout << i << ": " << resultado_formula << std::endl;	//imprime resultado
	}
	return 0;
}
