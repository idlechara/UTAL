/*********************************************
 * OPL 12.3 Model
 * Author: jvarred
 * Creation Date: 20 May 2015 at 21:29:03
 *********************************************/
dvar int+ X1;
dvar int+ X2;

maximize 3*X1 + 5*X2;
subject to{
	ct1:	X1		    <=	4;
	ct2:	       2*X2	<=	12;
	ct3:	3*X1 + 2*X2	<=	18;
}