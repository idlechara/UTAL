//============================================================================
// Name        : BinaryReader.cpp
// Author      : Erik Andres Regla Torres
// Version     :
// Copyright   : Copialo y te pego! D:
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <fstream>

using namespace std;

int main(int number, char **arguments) {
	ifstream fileop;
	int value;
	fileop.open(arguments[1], ios::in | ios::binary);
	fileop.read((char *) &value, sizeof(value));
	while (!fileop.eof()) {
		cout << value << endl;
		fileop.read((char *) &value, sizeof(value));
	}
	return 0;
}
