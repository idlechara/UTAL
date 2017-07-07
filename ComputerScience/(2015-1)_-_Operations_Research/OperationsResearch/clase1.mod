// range 	I = 1..2;
// range 	J = 1..3;
//
// dvar 	venta[I];
// int 	recurso[J]
// int		MatrizA[[]];

dvar int+ X1;
dvar int+ X2;

//maximize Venta[1]*X1 + Venta[2]*X2;
maximize 3*X1 + 5*X2;
subject to{
	ct1:	X1		    <=	4;
	ct1:	     + 2*X2	<=	12;
	ct1:	3*X1 + 2*X2	<=	18;
}

// maximize 3*X1 + 5*X2;
// subject to{
// 	ct1:	X1		    <=	4;
// 	ct1:	     + 2*X2	<=	12;
// 	ct1:	3*X1 + 2*X2	<=	18;
// }