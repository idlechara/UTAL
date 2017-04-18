/*
 * HeapPaging.cpp
 *
 *  Created on: Jul 9, 2012
 *      Author: kynku
 */

#include "HeapPaging.h"
#include "malloc.h"
#include "constants.h"
#include "limits.h"

namespace mempag {

HeapPaging::HeapPaging(int bufferSize):
	Paging(){
	this->memory = (t_value *) malloc(((this->bufferSize)) * sizeof(t_value));
}

HeapPaging::HeapPaging():
	Paging(){
}

HeapPaging::~HeapPaging() {
	free(memory);
}

void HeapPaging::push(t_value target) {
	int last = this->size();
	int aux = last;
	int parent = aux / 2;

	while (target.value < this->get(parent).value) {
		this->set(this->get(parent), aux);
		aux = parent;
		parent = aux / 2;
	}

	this->set(target, aux);

}

t_value HeapPaging::get(int index) {
	if (index == -1){
		t_value ret;
		ret.page = -1;
		ret.value = INT_MIN;
		return ret;
	}
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

void HeapPaging::set(t_value target, int index){
	if(index < 0 || index > this->bufferSize)
		throw "Array index out of bounds";
	this->memory[index] = target;
}

t_value HeapPaging::getMin() {
	if (this->size() <= 0){
		t_value ret;
		ret.page = -1;
		ret.value = INT_MIN;
		return ret;
	}
	return this->get(0);
}

t_value HeapPaging::extractMin() {
	if (this->size() <= 0){
		t_value ret;
		ret.page = -1;
		ret.value = INT_MIN;
		return ret;
	}
	t_value extracted = this->getMin();
	this->memory[0] = this->memory[this->size() - 1];
	this->actualSize--;
	percolateDown(0);
	return extracted;
}

void HeapPaging::percolateDown(int index) {
	t_value aux = this->get(index);
	int last = this->size() - 1;
	int leftchild, rightchild;
	int smaller;

	while (true) {
		rightchild = 1 + (leftchild = 2 * index);
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

void HeapPaging::dump(){

}
void HeapPaging::dump(std::ofstream channel){
	for(int i=0; i<this->actualSize; i++){
		channel.write((char *)&this->memory[i], sizeof(int));
	}
}

} /* namespace mempag */
