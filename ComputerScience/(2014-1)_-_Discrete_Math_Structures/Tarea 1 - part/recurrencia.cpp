#include <iostream>
#include <climits>
unsigned long long int recursion(unsigned long long int target){
	return (target > 2UL) ? recursion((target-1))*recursion((target-2)): target;
}

int main(){
	unsigned long long int target = 1UL;//, result = 0UL;
	while(target > 0){
		
		#ifdef VERBOSE
			std::cout << "Ingrese valor a evaluar: ";
		#endif


		#ifdef VERBOSE
			// std::cout << ULLONG_MAX << std::endl;
			std::cout << "Valor para usando recursion" << target << ": " << recursion(target) << std::endl;
		#endif


		std::cin >> target;

	}
	return 0;
}

// class BigNumber {
// 	//ULONG_MAX=18446744073709551615
//     unsigned int *value;
//     unsigned int *size;

//   public:
//   	//a fixed size btw
//     BigNumber(unsigned int size) {
//     	this-> size = size;
//     	this->value = new unsigned int [size];
//  	}
    
//     //override + operator and * operator
//     double circum() {return 2*radius*3.14159265;}
// };
