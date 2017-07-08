/*
 * DiskPageManager.cpp
 *
 *  Created on: Jul 9, 2012
 *      Author: kynku
 */

#include "DiskPageManager.h"
#include "malloc.h"
#include "../paging/constants.h"
#include "../paging/HeapPaging.h"
#include "../paging/Paging.h"
#include "sstream"
#include "string"

namespace diskpag {

DiskPageManager::DiskPageManager(std::string file, int generation, int ways, int pages) {
	this->generation = generation;
	this->originalFile = file;
	this->pages = pages;
}

DiskPageManager::~DiskPageManager() {
	// TODO Auto-generated destructor stub
}

DiskPageManager DiskPageManager::mergeGeneration() {
	int pagesWrited = 0, pagesReaded = 0;
	while (loadPages(pagesReaded)) {
		//load pages
		this->merge(pagesWrited++);
		//flush data

		//increment
		this->unloadPages(pagesReaded);
		pagesReaded+= ways;
		//liberatePages
	}

	return DiskPageManager(this->originalFile, this->generation+1, this->ways, this->pages);
	//if pages == 1, then rename and exit.
}

void DiskPageManager::unloadPages(int idx){

	int pagesRemaning =
			(this->pages - (idx + (ways - 1)) >= 0) ?
					ways : (this->pages - idx);

	for (int i = idx; i < idx + pagesRemaning; i++) {
				//TODO consultar sintaxis
				delete &this->diskPages[i];
				this->pageIndex[i] = idx+i;
	}
	free(this->diskPages);
	free(this->pageIndex);
}

bool DiskPageManager::loadPages(int idx) {
	int pagesRemaning =
			(this->pages - (idx + (ways - 1)) >= 0) ?
					ways : (this->pages - idx);

	if (pagesRemaning >= 0) {
		this->diskPages = (mempag::DiskPage *) malloc(
				sizeof(mempag::DiskPage) * pagesRemaning);

		this->pageIndex= (int *) malloc(
				sizeof(int) * pagesRemaning);
		for (int i = idx; i < idx + pagesRemaning; i++) {
			//TODO consultar sintaxis
			(&(this->diskPages[i])) = new mempag::DiskPage(this->originalFile,this->generation,idx+i);
			this->pageIndex[i] = idx+i;
		}
		return true;
	} else
		return false;
}

void DiskPageManager::merge(int actualPage) {
	//initialize outputPage
	this->output.open(
			this->getDestination(this->generation + 1, actualPage).data(),
			std::ios::out | std::ios::binary);

	//write the merged contents of all pages
	int aux = this->extractMin();
	while (aux != -1) {
		this->output.write((char *) &aux, sizeof(aux));
		aux = this->extractMin();
	}

	//close the stream
	this->output.close();
}

int DiskPageManager::extractMin() {
	t_value aux = this->buffer.extractMin();

	//consumes the page
	if (this->diskPages[aux.page].hasNext()) {
		t_value temp;
		temp.value = this->diskPages[aux.page].readNext();
		temp.page = aux.page;
		this->buffer.push(temp);
	}

	return aux.value;
}

int DiskPageManager::getPageIdx(int pageIdx) {
	for (int i = 0; i < this->pages; i++) {
		if (this->pageIndex[i] == pageIdx) {
			return i;
		}
	}
	return -1;
}

bool DiskPageManager::hasNext() {
	if (this->buffer.getMin().value != -1)
		return true;
	return false;
}

std::string DiskPageManager::getDestination(int generation, int page) {

	std::stringstream targetFile;
	targetFile.clear();
	targetFile << this->originalFile << "_" << generation << "_" << page;
	return targetFile.str();
}

} /* namespace diskpag */
