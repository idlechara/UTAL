/*
 * Manager.h
 *
 *  Created on: Jul 12, 2012
 *      Author: kynku
 */

#ifndef MANAGER_H_
#define MANAGER_H_

#include "../paging/HeapPaging.h"
#include "fstream"

namespace diskpag {

class Manager {
public:
	int mergeGeneration();
	bool dataAvaliable();
	bool getData();
	Manager(std::string fileName, int generation, int pages, int ways);
	virtual ~Manager();

private:
	void loadHeap();
	void freePageSpace();
	void initPageSpace();
	int extractFromHeap();
	void merge(int actualPage);
	std::string filename;
	bool isFileAvaliable(int index);
	int pages, ways, readed, pagesToLoad, generation;
	mempag::HeapPaging *heap;
	std::ifstream **inputArray;
};

} /* namespace diskpag */
#endif /* MANAGER_H_ */
