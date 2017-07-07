/*
 * Manager.cpp
 *
 *  Created on: Jul 12, 2012
 *      Author: kynku
 */

#include "Manager.h"
#include "sstream"
#include "fstream"

namespace diskpag {

Manager::Manager(std::string fileName, int generation, int pages, int ways) {
	this->filename = fileName;
	this->generation = generation;
	this->pages = pages;
	this->ways = ways;
	this->readed = 0;
}

int Manager::mergeGeneration() {
	int actualPage = 0;

	for (int i = 0; i < pages; i += ways) {
		initPageSpace();
		std::cout << "Manager space allocated!" << std::endl;
		merge(actualPage);
		std::cout << "Merge executed!" << std::endl;
		freePageSpace();
		std::cout << "Space freed!" << std::endl;
		actualPage++;
	}

	return actualPage;
}

void Manager::merge(int actualPage) {

	std::stringstream targetPage;
	std::ofstream output;

	bool nonEndOfFile = true;
	targetPage.str("");
	targetPage << this->filename << "_" << this->generation+1 << "_"
			<< actualPage;
	output.open(targetPage.str().data(), std::ios::out | std::ios::binary);

	int temp;

	std::cout << "Output stream ready!" << std::endl;
	while (!this->heap->isEmpty()) {

		std::cout << "extracting iteration!!" << std::endl;
		if (this->heap->isFull()) {
			this->heap->dump(&output);
		}

		temp = this->extractFromHeap();
		if (temp == -1) {
			nonEndOfFile = false;
			if (pages >= 0)
				this->heap->dump(&output);
		}

	}

	this->heap->dump(&output);

	output.close();
}

void Manager::initPageSpace() {
	this->pagesToLoad =
			(this->pages - readed > this->ways) ?
					this->ways : this->pages - readed;

	std::cout << "Manager space allocated! - preparing inputArray!"
			<< std::endl;

	std::cout << "index!!! " << this->pagesToLoad;

	this->inputArray = new std::ifstream *[this->pagesToLoad];

	std::cout << "Manager space allocated - inputArray Ready!" << std::endl;

	std::stringstream targetFile;
	for (int i = 0; i < this->pagesToLoad; i++) {
		targetFile.str("");
		targetFile << this->filename << "_" << this->generation << "_"
				<< this->readed;
		std::cout << "page " << targetFile.str() << " loading!" << std::endl;
		this->inputArray[i] = new std::ifstream;
		this->inputArray[i]->open(targetFile.str().data(),
				std::ios::out | std::ios::binary);
		readed++;
		std::cout << "page " << targetFile.str() << " loaded!" << std::endl;
	}

	//loadheap
	this->loadHeap();
	std::cout << "Initial space allocation ready!" << std::endl;
}

int Manager::extractFromHeap() {
	if (this->heap->isEmpty())
		return -1;

	t_pageValue extracted = this->heap->extractMin();

	//std::cout << "heapexracted!! - reloading from page " << extracted.page << std::endl;

	if (!this->inputArray[extracted.page]->eof()) {
		//std::cout << "heapexracted!! - now prosessing" << extracted.page << std::endl;
		t_pageValue pageValue_;
		int temp;
		this->inputArray[extracted.page]->read((char *) &temp, sizeof(int));
		pageValue_.value = temp;
		pageValue_.page = extracted.page;
		this->heap->push(pageValue_);
	}
	std::cout << "heapexracted!! - reloading from page " << extracted.value<< std::endl;
	return extracted.value;
}

void Manager::loadHeap() {

	std::cout << "loading heap..." << std::endl;
	this->heap = new mempag::HeapPaging(pagesToLoad);
	int temp;
	for (int i = 0; i < this->pagesToLoad; i++) {
		t_pageValue pageValue;
		this->inputArray[i]->read((char *) &temp, sizeof(int));
		pageValue.value = temp;
		pageValue.page = i;
		this->heap->push(pageValue);
	}
}

void Manager::freePageSpace() {
	for (int i = 0; i < this->pagesToLoad; i++) {
		delete this->inputArray[i];
	}

	delete[] inputArray;
	delete heap;
}

Manager::~Manager() {
	// TODO Auto-generated destructor stub
}

} /* namespace diskpag */
