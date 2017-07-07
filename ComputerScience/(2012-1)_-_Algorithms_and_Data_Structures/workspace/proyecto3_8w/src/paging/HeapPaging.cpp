/*
 * HeapPaging.cpp
 *
 *  Created on: Jul 9, 2012
 *      Author: kynku
 */

#include "HeapPaging.h"
#include "malloc.h"
#include "../structs/structs.h"
#include "limits.h"
#include "iostream"
#include "sstream"

namespace mempag {

HeapPaging::HeapPaging(int bufferSize):
	Paging(){
	this->bufferSize = bufferSize+1;
	this->memory = (t_pageValue *) malloc( ((this->bufferSize)+1) * sizeof(t_pageValue));
	this->actualSize = 0;
	this->iteratorPos = 1;
	t_pageValue temp;
	temp.page = -1;
	temp.value = INT_MIN;
	this->memory[0] = temp;
	//std::cout << "acutal lenght: " << this->actualSize;
}

HeapPaging::HeapPaging():
	Paging(){
}

HeapPaging::~HeapPaging() {
	free(memory);
}

void HeapPaging::push(int target){

	//std::cout << "push! " << target << std::endl;
	t_pageValue temp;
	temp.page = -1;
	temp.value = target;

	//std::cout << "push()!! lenght: " << this->actualSize << std::endl;
	this->push(temp);
}

void HeapPaging::push(t_pageValue target) {
	int last = ++this->actualSize;
	int aux = last;
	int parent = aux / 2;


	//std::cout << "parent value: " << parent << "lenght: " << this->actualSize << std::endl;

	while (target.value < this->get(parent).value) {
		this->set(this->get(parent), aux);
		aux = parent;
		parent = aux / 2;
		//std::cout << "parent value: " << parent<< std::endl;
	}

	//std::cout << "drooping onto position: " << aux<< std::endl;
	this->set(target, aux);

}

t_pageValue HeapPaging::get(int index) {
	if (index < 0){
		std::stringstream err;
		err << "Error: Negative array buffer!" << index;
		throw err.str().data();
	}
	if (index > this->bufferSize){
		std::stringstream err;
		err << "Error: Array index out of bounds!" << index;
		throw err.str().data();
	}
	if (index > this->actualSize+1){	//es por que el arreglo parte desde el 1 por culpa del MIN_INT
		std::stringstream err;
		err << "Error: Memory page index without data! index:" << index << " actualSize: " << this->actualSize;
		throw err.str().data();
	}
//	if (this->isEmpty())
//		throw "Error: Buffer underflow!";

	return this->memory[index];
}

void HeapPaging::set(t_pageValue target, int index){
	if(index < 0 || index > this->bufferSize){
		std::stringstream err;
		err << "Array index out of bounds" << index;
		throw err.str().data();
	}
	this->memory[index] = target;
}

t_pageValue HeapPaging::getMin() {
	//std::cout << " actualSize: " << this->actualSize <<std::endl;
	if (this->actualSize <= 0){
		t_pageValue ret;
		ret.page = -1;
		ret.value = INT_MIN;
		return ret;
	}
	return this->get(1);
}

t_pageValue HeapPaging::extractMin() {
	if (this->actualSize <= 0){
		t_pageValue ret;
		ret.page = -1;
		ret.value = INT_MIN;
		return ret;
	}
	t_pageValue extracted = this->getMin();
	this->memory[1] = this->memory[this->actualSize--];
	percolateDown(1);
//	std::cout << extracted.value << std::endl;
	return extracted;
}

void HeapPaging::percolateDown(int index) {
	t_pageValue aux = this->get(index);
	int last = this->size() - 1;
	int leftchild, rightchild;
	int smaller;

	while (true) {
		leftchild = 2 * index;
		rightchild = 1 + (leftchild);

		if (leftchild > last)
			break;
		else {
			if ((leftchild == last)
					|| (this->get(leftchild).value < this->get(rightchild).value))
				smaller = leftchild;
			else
				smaller = rightchild;
		}

		if(this->get(smaller).value < aux.value){
			this->set(this->get(smaller), index);
			index = smaller;
		}
		else break;
	}

	this->set(aux, index);
}

void HeapPaging::dump(std::ofstream *channel){
	int temp;
	while (this->actualSize>1){
		//channel.write((char *)&this->memory[i].value, sizeof(int));
		temp = this->extractMin().value;
		channel->write((char *)&temp, sizeof(int));
		//std::cout << "WRITTING " << "of" << this->actualSize <<"!: " << temp << std::endl;
	}

	channel->flush();
	//std::cout << "dump terminated!" << std::endl;
}

} /* namespace mempag */
