/*
 * FileWritter.cpp
 *
 *  Created on: Jul 13, 2012
 *      Author: kynku
 */

#include "FileWritter.h"
#include "iostream"
#include "fstream"
#include "sstream"
#include "malloc.h"

namespace diskpag {

void FileWritter::init(int generation, int page) {
	std::stringstream targetFilename;
	targetFilename.str("");
	targetFilename << filename << "_"<< generation << "_" << page;
	this->fileOutput.open(targetFilename.str().data(), std::ios::out | std::ios::binary);
}

FileWritter::FileWritter(std::string filename, int bufSize) {
	this->filename = filename;
	this->bufSize = bufSize;
	this->buf = (int *)malloc(bufSize * sizeof(int));
	this->actualSize = 0;
}

void FileWritter::write(int data){
	if(this->actualSize >= bufSize) this->dump();
	this->buf[actualSize++] = data;
}

void FileWritter::closeAndFlush(){
	if(actualSize>0) this->dump();
	this->fileOutput.close();
	this->actualSize = 0;
}

void FileWritter::dump(){
	for(int i=0; i<this->actualSize; i++){
		this->fileOutput.write((char *)&this->buf[i],sizeof(int));
	}
	this->fileOutput.flush();
	this->actualSize = 0;
}

FileWritter::~FileWritter() {
	free(this->buf);
}


} /* namespace diskpag */
