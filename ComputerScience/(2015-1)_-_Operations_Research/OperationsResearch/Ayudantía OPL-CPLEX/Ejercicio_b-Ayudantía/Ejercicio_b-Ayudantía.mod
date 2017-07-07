/*********************************************
 * OPL 12.6.0.0 Model
 * Author: Diego Valenzuela
 * Creation Date: 01-06-2015 at 16:10:38
 *********************************************/

  //Definir Rango
range I= 1..40;
range J= 1..15;

//definir parámetros
int CostosTransporte[I][J]=...;
int CostosOperacionales [J]=...;
int CostosFijos [J]=...;
int Toneladas[I] =...;

//definición de Variables
dvar float+ x[I][J];
dvar boolean  y[J];

//Funcion Objetivo

minimize  sum (j in J)CostosFijos[j]*y[j]  +  sum(i in I)(sum(j in J)CostosOperacionales[j]*x[i][j]) + sum (i in I)(sum(j in J)CostosTransporte[i][j]*x[i][j] );

//Restricciones

subject to {

  forall (i in I) 
  	{
  	sum (j in J)x[i][j] >= Toneladas[i];  //Cada área puede ser atendida por un único local.
 	}  	
  forall (j in J)
    {
    sum (i in I) x[i][j]<=3750*y[j]; //Capacidad del local para recibir residuos.
    }
    }    