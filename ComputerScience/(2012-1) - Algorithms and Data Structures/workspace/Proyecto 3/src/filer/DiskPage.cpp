/*
 * DiskPage.cpp
 *
 *  Created on: Jul 9, 2012
 *      Author: kynku
 */

#include <iostream>
#include <fstream>
#include <cstdlib>
//#include "IntegerArray.h"
#include "limits.h"
#include "stdlib.h"
#include "sstream"

#include "DiskPage.h"

namespace mempag {

DiskPage::DiskPage(std::string file, int generation, int page) {
	this->generation = generation;
	this->page = page;
	this->originalFile = file;
	this->targetFile.clear();
	this->targetFile << this->originalFile << "_" << this->generation << "_" << this->page;
	this->inputStream.open(targetFile.str().data(), std::ios::in | std::ios::binary);
	this->readNext();
}

DiskPage::~DiskPage() {
	this->inputStream.close();
}

bool DiskPage::hasNext() {
	return (this->buffer >= 0);
}

int DiskPage::readNext() {
	int buf = this->buffer;
	if (!this->inputStream.eof()) {
		int value;
		inputStream.read((char *) &value, sizeof(value));
		this->buffer = value;
	} else
		this->buffer = -1;

	return buf;
}

} /* namespace diskpag */
