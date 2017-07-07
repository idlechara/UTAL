/*
 * FileReader.cpp
 *
 *  Created on: Jul 12, 2012
 *      Author: kynku
 */

#include "FileReader.h"
#include "sstream"
#include "iostream"

namespace diskpag {

FileReader::FileReader(std::string filename, int generation, int page) {
	std::stringstream targetFilename;
	targetFilename.str("");
	targetFilename << filename << "_"<< generation << "_" << page;
	this->fileInput.open(targetFilename.str().data(), std::ios::in | std::ios::binary);
}

bool FileReader::hasData(){
	return !this->fileInput.eof();
}

int FileReader::nextInt(){
	if(this->fileInput.eof()) return -1;
	else{
		int temp = -1;
		this->fileInput.read((char *)&temp, sizeof(int));
		return temp;
	}
}

FileReader::~FileReader() {
	this->fileInput.close();
}

} /* namespace diskpag */
