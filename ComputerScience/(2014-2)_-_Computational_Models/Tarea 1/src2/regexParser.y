

%{
#define YYSTYPE double
#include <ctype.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>

double tabla[25];

%}

%token NUM

%%
lineas      :   linea lineas
            |
            ;

linea       :   expresion '\n'                      {   printf("%f\n-> ", $1);  }
            |   '(' variable '=' expresion ')' '\n' {   $2 = $3;   }
            ;

expresion   :   '(' operacion ')'                   {   $$ = $2;    }
            |   NUM                                 {   $$ = $1;    }
            ;

operacion   :   NUM                                 {   $$ = $1;                }
            |   '+' lista_suma     expresion        {   $$ = $2 + $3;           }
            |   '-' lista_resta    expresion        {   $$ = $2 - $3;           }
            |   '*' lista_mult     expresion        {   $$ = $2 * $3;           }
            |   '/' lista_div      expresion        {   $$ = $2 / $3;           }
            |   'N' expresion      expresion        {   $$ = -$2;               }
            |   '^' expresion      expresion        {   $$ = pow($2,$3);        }          
            |   variable                            {   $$ = $1;  }
            ;

lista_suma  :   lista_suma  expresion               {   $$ = $1 + $2; }
            |   expresion                           {   $$ = $1;      }
            ;

lista_resta :   lista_resta  expresion              {   $$ = $1 - $2; }
            |   expresion                           {   $$ = $1;      }
            ;

lista_mult  :   lista_mult  expresion               {   $$ = $1 * $2; }
            |   expresion                           {   $$ = $1;      }
            ;

lista_div   :   lista_div  expresion                {   $$ = $1 / $2; }
            |   expresion                           {   $$ = $1;      }
            ;

variable    : 'a'                                   { $$ = tabla[0]; } 
            | 'b'                                   { $$ = tabla[1]; } 
            | 'c'                                   { $$ = tabla[2]; } 
            | 'd'                                   { $$ = tabla[3]; } 
            | 'e'                                   { $$ = tabla[4]; } 
            | 'f'                                   { $$ = tabla[5]; } 
            | 'g'                                   { $$ = tabla[6]; } 
            | 'h'                                   { $$ = tabla[7]; } 
            | 'i'                                   { $$ = tabla[8]; } 
            | 'j'                                   { $$ = tabla[9]; } 
            | 'k'                                   { $$ = tabla[10]; } 
            | 'l'                                   { $$ = tabla[11]; } 
            | 'm'                                   { $$ = tabla[12]; } 
            | 'n'                                   { $$ = tabla[13]; } 
            | 'o'                                   { $$ = tabla[14]; } 
            | 'p'                                   { $$ = tabla[15]; } 
            | 'q'                                   { $$ = tabla[16]; } 
            | 'r'                                   { $$ = tabla[17]; } 
            | 's'                                   { $$ = tabla[18]; } 
            | 't'                                   { $$ = tabla[19]; } 
            | 'u'                                   { $$ = tabla[20]; } 
            | 'v'                                   { $$ = tabla[21]; } 
            | 'w'                                   { $$ = tabla[22]; } 
            | 'x'                                   { $$ = tabla[23]; } 
            | 'y'                                   { $$ = tabla[24]; } 
            | 'z'                                   { $$ = tabla[25]; } 
            | 'A'                                   { $$ = tabla[26]; } 
            | 'B'                                   { $$ = tabla[27]; } 
            | 'C'                                   { $$ = tabla[28]; } 
            | 'D'                                   { $$ = tabla[29]; } 
            | 'E'                                   { $$ = tabla[30]; } 
            | 'F'                                   { $$ = tabla[31]; } 
            | 'G'                                   { $$ = tabla[32]; } 
            | 'H'                                   { $$ = tabla[33]; } 
            | 'I'                                   { $$ = tabla[34]; } 
            | 'J'                                   { $$ = tabla[35]; } 
            | 'K'                                   { $$ = tabla[36]; } 
            | 'L'                                   { $$ = tabla[37]; } 
            | 'M'                                   { $$ = tabla[38]; } 
            | 'N'                                   { $$ = tabla[39]; } 
            | 'O'                                   { $$ = tabla[40]; } 
            | 'P'                                   { $$ = tabla[41]; } 
            | 'Q'                                   { $$ = tabla[42]; } 
            | 'R'                                   { $$ = tabla[43]; } 
            | 'S'                                   { $$ = tabla[44]; } 
            | 'T'                                   { $$ = tabla[45]; } 
            | 'U'                                   { $$ = tabla[46]; } 
            | 'V'                                   { $$ = tabla[47]; } 
            | 'W'                                   { $$ = tabla[48]; } 
            | 'X'                                   { $$ = tabla[49]; } 
            | 'Y'                                   { $$ = tabla[50]; } 
            | 'Z'                                   { $$ = tabla[51]; }
            ;

%%
yylex (){
    int c;
    while ((c = getchar ()) == ' ' || c == '\t');
    
    if (c == '.' || isdigit (c)){
      ungetc (c, stdin);
      scanf ("%lf", &yylval);
      return NUM;
    }

    if (c == EOF)                            
        return 0;
    return c;                                
}

yyerror( char *s){
    printf("Error de sintaxis\n");
}

main(){
    printf ("-> ");
    yyparse();
}