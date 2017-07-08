/*
 * FileArray.h
 *
 *  Created on: Jul 12, 2012
 *      Author: kynku
 */

#ifndef FILEARRAY_H_
#define FILEARRAY_H_

#include "iostream"
#include "FileReader.h"
#include "../structs/structs.h"

namespace diskpag {

class FileArray {
public:
	int readInt(int page);
	t_pageValue readStruct(int page);
	FileArray(int size, std::string filename);
	void add(int generation, int page);
	void get(int page);
	bool hasData();
	int size();
	bool empty();
	bool full();
	void reset();
	virtual ~FileArray();

private:
	std::string filename;
	diskpag::FileReader **fileArray;
	int bufSize, actualSize;
	//mempag::HeapPaging *buf;
};

} /* namespace diskpag */
#endif /* FILEARRAY_H_ */
