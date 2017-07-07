/*********************************************
 * OPL 12.6.0.0 Model
 * Author: Diego Valenzuela
 * Creation Date: 01-06-2015 at 15:41:15
 *********************************************/
 
 //Definir Rango
range I= 1..40;
range J= 1..15;

//definir par�metros
int CostosTransporte[I][J]=...;
int CostosOperacionales [J]=...;
int CostosFijos [J]=...;
int Toneladas[I] =...;
int M = ...;

//definici�n de Variables
dvar boolean x[I][J];
dvar boolean  y[J];

//Funcion Objetivo

minimize  sum (j in J)CostosFijos[j]*y[j]  +  sum(i in I)(sum(j in J) Toneladas[i]*CostosOperacionales[j]*x[i][j]) + sum (i in I)(sum(j in J) Toneladas[i]*CostosTransporte[i][j]*x[i][j] );

//Restricciones

subject to {

  forall (i in I) 
  	{
  	sum (j in J)x[i][j]==1;  //Cada �rea puede ser atendida por un �nico local.
 	}  	
  forall (j in J)
    {
    sum (i in I) x[i][j]*Toneladas[i]<=M*y[j]; //Capacidad del local para recibir residuos.
    }
    }    		
 
 
 
 
 
 
 
 