//============================================================================
// Name        : Proyecto.cpp
// Author      : Erik Andres Regla Torres
// Version     :
// Copyright   : Copialo y te pego! D:
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <fstream>
#include <cstdlib>
//#include "IntegerArray.h"
#include "limits.h"
#include "stdlib.h"
#include "sstream"

using namespace std;

void initPages (char *, char *, int);
void _sortAndWritePage(string target, int *buf, int len);

int main() {

	//initPages(NULL, NULL, 10);

	cout << "limits: " << INT_MIN;
	return 0;
}

void initPages(char *target, char *destination, int pageSize) {
	int pages = 0;
	int pageIdx = 0;
	int actualValue = 0;
	int *buf;

	buf = (int *)malloc((sizeof(int)) * pageSize);
	ifstream input, output;
	input.open("/home/kynku/output.txt", ios::in | ios::binary);

	stringstream targetPage;
	targetPage << "/home/kynku/output" << pages << ".txt";

	bool nonEndOfFile = true;

	while (nonEndOfFile) {
		input.read( (char *)&actualValue, sizeof(actualValue) );
		buf[pageIdx] = actualValue;

		cout << "reading: " << actualValue << " - page: " << pages << " -  index: " << pageIdx << endl;

		if (++pageIdx == pageSize) {
			output.close();

			_sortAndWritePage(targetPage.str() , buf, pageIdx);
			targetPage.str(std::string());
			targetPage << "/home/kynku/output" << ++pages << ".txt";

			pageIdx = 0;
		}

		if(input.eof()) {

			cout << "endOfFile! " << endl;
			nonEndOfFile = false;
			output.close();
			if(pageIdx > 0)
			_sortAndWritePage(targetPage.str(), buf, pageIdx);
		}

		if(!input.is_open()){

			cout << "error! " << endl;
			nonEndOfFile = false;
			output.close();
		}
	}
}

void _sortAndWritePage(string target, int *buf, int len) {
	ofstream output;
	output.open(target.data(), ios::out | ios::binary);

	int value;

	cout << "newPage! " << endl;
	for (int i = 0; i < len; i++) {
		value = buf[i];
		cout << "saving " << value << endl;
		output.write((char *) &value, sizeof(value));
	}
	output.close();
}

//TODO

/**
 * implementar split!
 *
 * implementar paso de parametros
 *
 *
 */

