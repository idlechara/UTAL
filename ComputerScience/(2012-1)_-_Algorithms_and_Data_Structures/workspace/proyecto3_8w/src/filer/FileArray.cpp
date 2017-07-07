/*
 * FileArray.cpp
 *
 *  Created on: Jul 12, 2012
 *      Author: kynku
 */

#include "FileArray.h"
#include "FileReader.h"

namespace diskpag {

FileArray::FileArray(int size, std::string filename) {
	this->filename = filename;
	this->bufSize = size;
	this->actualSize = 0;
	this->fileArray = new diskpag::FileReader*[size];
}

FileArray::~FileArray() {
	for (int i = 0; i < this->actualSize; i++) {
		delete this->fileArray[i];
	}
	delete[] fileArray;
}

int FileArray::readInt(int page) {
	if (page >= this->bufSize)
		throw "Error: Array index out of bounds!";
	return this->fileArray[page]->nextInt();
}

t_pageValue FileArray::readStruct(int page) {
	if (page >= this->bufSize)
		throw "Error: Array index out of bounds!";

	t_pageValue ret;
	ret.value = fileArray[page]->nextInt();
	ret.page = page;

	return ret;
}

bool FileArray::hasData() {
	for (int i = 0; i < this->actualSize; i++)
		if (this->fileArray[i]->hasData())
			return true;
	return false;
}

void FileArray::reset() {
	for (int i = 0; i < this->actualSize; i++) {
		this->fileArray[i]->~FileReader();
	}
	this->actualSize = 0;
}

bool FileArray::empty() {
	return this->actualSize <= 0;
}

int FileArray::size() {
	return this->actualSize;
}

bool FileArray::full() {
	return this->actualSize >= this->bufSize;
}

void FileArray::add(int generation, int page) {
	if (this->actualSize >= this->bufSize)
		throw "Error: File limit reached!";

	this->fileArray[this->actualSize++] = new FileReader(this->filename,
			generation, page);
}

} /* namespace diskpag */
