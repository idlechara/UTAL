/*
 * FileWritter.h
 *
 *  Created on: Jul 13, 2012
 *      Author: kynku
 */

#ifndef FILEWRITTER_H_
#define FILEWRITTER_H_
#include "iostream"
#include "fstream"
#include "../paging/HeapPaging.h"

namespace diskpag {

class FileWritter {
public:
	void init(int generation, int page);
	void write(int data);
	void closeAndFlush();
	FileWritter(std::string filename, int bufSize);
	virtual ~FileWritter();
private:
	void dump();
	std::ofstream fileOutput;
	std::string filename;
	int bufSize;
	int *buf;
	int actualSize;
};

} /* namespace diskpag */
#endif /* FILEWRITTER_H_ */
