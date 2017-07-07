/*
 * Paging.cpp
 *
 *  Created on: Jul 5, 2012
 *      Author: kynku
 */

#include "Paging.h"
#include "malloc.h"
#include "fstream"
#include "limits.h"


namespace mempag {


Paging::Paging(int bufSize) {
	// TODO Auto-generated constructor stub
	this->bufferSize = bufferSize;
	this->actualSize = 0;
	this->memory = (int *) malloc(((int) (this->bufferSize)) * sizeof(int));
	this->iteratorPos = 0;
}

Paging::Paging() {
	// TODO Auto-generated constructor stub
	this->bufferSize = bufferSize;
	this->actualSize = 0;
	this->iteratorPos = 0;
}

Paging::~Paging() {
	free(memory);
}

bool Paging::isFull() {
	return (this->actualSize >= this->bufferSize);
}

bool Paging::isEmpty() {
	return (this->actualSize <= 0);
}

int Paging::get(int index) {
	if (index == -1)
		return INT_MIN;
	if (index < 0)
		throw "Error: Negative array index!";
	if (index > this->bufferSize)
		throw "Error: Array index out of bounds!";
	if (index >= actualSize)
		throw "Error: Memory page index without data!";
	if (this->isEmpty())
		throw "Error: Buffer underflow!";

	return this->memory[index];
}

void Paging::push(int target) {
	if (this->isFull())
		throw "Error: Buffer overflow!";
	this->memory[actualSize++] = target;
}

int Paging::size(){
	return this->actualSize;
}

void Paging::set(int target, int index){
	if(index < 0 || index > this->bufferSize)
		throw "Array index out of bounds";
	this->memory[index] = target;
}

void Paging::clear(){
	this->actualSize = 1;
}

void Paging::resetIterator(){
	this->iteratorPos = 0;
}

int Paging::iterate(){
	return this->get(this->iteratorPos);
}

bool Paging::writePage(std::string target){
	std::fstream output;

	output.open(target.data(), std::ios::out | std::ios::binary);

	int value;

	std::cout << "newPage! " << std::endl;
	for (int i = 0; i < this->actualSize; i++) {
		value = this->memory[i];
		std::cout << "saving " << value << std::endl;
		output.write((char *) &value, sizeof(value));
	}
	output.close();

	return true;
}

} /* namespace mempag */
