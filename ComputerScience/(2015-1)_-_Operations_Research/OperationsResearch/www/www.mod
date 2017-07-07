/*********************************************
 * OPL 12.3 Model
 * Author: jvarred
 * Creation Date: 1 Jun 2015 at 16:48:25
 *********************************************/

//definir rango
 range I= 1..40;
 range J= 1..15;
 
 //definir parámetros
 int CostosTransporte[I][J]=...;
 int CostosOperacionales[J];
 int CostosFijos[J]=...;
 int Toneladas[I]=...;
 int M= ...;
 
 //definiciones de variables
 dvar boolean x[I][J];
 dvar boolean y[J];
 
 //funcion objetivo
 minimize	sum(j in J) 	( CostosFijos[j]*y[j] ) + 
 			sum(i in I)		( sum(j in J)CostosOperacionales[j]*x[i][j]) + 
 			sum (i in I)	( sum(j in J)CostosTransporte[i][j]*x[i][j] );
 
 //restricciones
 subject to{
   forall(i in I){
     sum(j in J) x[i][j] == 1;
   }
   
   forall(j in J){
     sum(i in I) x[i][j]*Toneladas[i] <= M*y[j];
   }     
 }
 