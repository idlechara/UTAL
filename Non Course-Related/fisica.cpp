// Copyright (c) 2014 Erik Andrés Regla Torres

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

#include <iostream>
#include <cmath>
#include <string>
#include <vector>

class Miltoniano{
public:
	double valor;
	double incerteza;
	int numero_decimales;

	Miltoniano(double valor = 0.0, double incerteza = 0.0, int numero_decimales = 2){
		this-> valor = valor;
		this-> incerteza = incerteza;
		this-> numero_decimales = numero_decimales;
		this->aproximar();
	}
	
	Miltoniano aproximar(){
		int potencia = (int)(pow(10, numero_decimales));
		this->valor = ((int)round(valor * ((double)(potencia)))) / ((double)(potencia));
		this->incerteza = (double)((int)round(incerteza * ((double)(potencia)))) / ((double)(potencia));
		this->incerteza = (this->incerteza < 1.0/(potencia*100)) ? 0.0 : this->incerteza; 
		return *this;
	}

	Miltoniano operator+(const Miltoniano& rhs){
	    Miltoniano temp = *this;
	    temp.valor += rhs.valor;
	    temp.incerteza += rhs.incerteza;
	    return temp.aproximar();
	}

	Miltoniano operator-(const Miltoniano& rhs){
	    Miltoniano temp = *this;
	    temp.valor -= rhs.valor;
	    temp.incerteza += rhs.incerteza;
	    return temp.aproximar();
	}

	Miltoniano operator*(const Miltoniano& rhs){
	    Miltoniano temp = *this;
	    temp.valor *= rhs.valor;
	    temp.incerteza = (rhs.incerteza * temp.valor) 
	    				+ (rhs.valor * temp.incerteza) 
	    				+ (rhs.incerteza * temp.incerteza);
	    return temp.aproximar();
	}

	Miltoniano operator/(const Miltoniano& rhs){
	    Miltoniano temp = *this;
	    temp.valor /= rhs.valor;
	    temp.incerteza = (temp.valor / rhs.valor)
	    				* ((rhs.incerteza / rhs.valor) +  (temp.incerteza / temp.valor));
	    return temp.aproximar();
	}

	friend std::ostream& operator<<(std::ostream &strm, const Miltoniano &target) {
  		return strm << target.valor << "±" << target.incerteza;
  	}

};


int main(void){
	// TESTING DATA
	
	int rows = 12, columns = 2;
	std::string input = "";
	std::vector<std::vector<Miltoniano>> results(rows);
	
	//insert raw data

	
	Miltoniano masa(1003.4, 0.05); 
	masa = masa / Miltoniano(1000);		//Conversion to kilograms
	std::cout << "Masa: " << masa << "KG" << std::endl;
	results[0].push_back(	Miltoniano(2,		0.1));
	results[1].push_back(	Miltoniano(4.4,		0.1));
	results[2].push_back(	Miltoniano(7.3,		0.1));
	results[3].push_back(	Miltoniano(10.8, 	0.1));
	results[4].push_back(	Miltoniano(14.8, 	0.1));
	results[5].push_back(	Miltoniano(19, 		0.1));
	results[6].push_back(	Miltoniano(23.8, 	0.1));
	results[7].push_back(	Miltoniano(29.2, 	0.1));
	results[8].push_back(	Miltoniano(34.7, 	0.1));
	results[9].push_back(	Miltoniano(41, 		0.1));
	results[10].push_back(	Miltoniano(47.7, 	0.1));
	results[11].push_back(	Miltoniano(55.8, 	0.1));

	//populate timing
	double counter = 0.1;
	for (int i = 0; i < rows; ++i, counter += 0.1)
	{
		//results[i][0] = results[i][0] / Miltoniano(100);	//Conversion to meters
		results[i][0] = results[i][0] / Miltoniano(10);	//Conversion to decameron
		results[i].push_back(Miltoniano(counter));
	}

	//Insert formula 1
	results[0].push_back(results[0][0] / results[0][1]);
	for (int i = 1; i < rows; ++i, counter += 0.1)
	{
		results[i].push_back((results[i][0] - results[i-1][0])
							 / (results[i][1] - results[i-1][1]));
	}

	// //Formula 2 = Ktras

	Miltoniano altura_maxima = /*Miltoniano(1.2) +*/ (Miltoniano(4.2351*3.14159265/180) * results[11][0]);
	for (int i = 0; i < rows; ++i, counter += 0.1)
	{
		results[i].push_back(Miltoniano(0.5) * masa * results[i][2] * results[i][2] );
		results[i].push_back(Miltoniano(0.25) * masa * results[i][2] * results[i][2] );
		results[i].push_back( masa * Miltoniano(98) * (altura_maxima - (Miltoniano(4.2351*3.14159265/180) * results[i][0])));
		results[i].push_back(	( masa * Miltoniano(98) * (altura_maxima - (Miltoniano(4.2351*3.14159265/180) * results[i][0])))
								+(Miltoniano(0.25) * masa * results[i][2] * results[i][2] )
								+(Miltoniano(0.5) * masa * results[i][2] * results[i][2] )
							);
		
	}

	std::cout << "Altura: " << (altura_maxima - (Miltoniano(4.2351*3.14159265/180) * results[11][0])) << "metros" << std::endl;
	//and so on. Remeber, if you need to insert a formula with a
	// A_-1 or +1, you need to do first or last in a separate line.

	//Printing Header
	std::cout 	<< "X" << "   \t\t" 
				<< "T" << "  \t\t"  
				<< "Vel.Media" << "  \t"
				<< "Ktras" << "  \t\t\t"
				<< "Krot" << "  \t\t\t" 
				<< "Ug" << "  \t\t\t" 
				<< "T" << std::endl;
	//Printing Rows
	for (int i = 0; i < rows; ++i, counter += 0.1)
	{
		std::cout << "--------------------------------------" <<
					"--------------------------------------" <<
					// "--------------------------------------" <<
				   	// "--------------------------------------" <<
				   	"------------------------------------------------" << std::endl;
		std::cout 	<< results[i][0] << "     \t" 
					<< results[i][1] << "  \t\t" 
					<< results[i][2] << "\t\t" 
					<< results[i][3] << "  \t\t" 
					<< results[i][4] << "  \t\t" 
					<< results[i][5] << "\t\t" 
					<< results[i][6] << std::endl;
	}


	// TESTING DATA
	// Miltoniano test_1(125.255,0.112121);
	// Miltoniano test_2(125.2,0.11);

	// std::cout << test_1 << std::endl;
	// std::cout << (test_1/Miltoniano(100,111)) << std::endl;
	// std::cout << results.size() << std::endl;
	// std::cout << results[1].size() << std::endl;

	return 0;
}