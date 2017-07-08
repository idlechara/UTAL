/*
 * DiskPageManager.h
 *
 *  Created on: Jul 9, 2012
 *      Author: kynku
 */

#ifndef DISKPAGEMANAGER_H_
#define DISKPAGEMANAGER_H_

#include "DiskPage.h"
#include "../paging/HeapPaging.h"
#include "fstream"
#include "iostream"

namespace diskpag {

class DiskPageManager {
public:
	//DiskPageManager();
	void merge(int actualPage);
	DiskPageManager mergeGeneration();
	DiskPageManager(std::string file, int generation, int ways, int pages);
	virtual ~DiskPageManager();

private:
	int getPageIdx(int pageIdx);
	void unloadPages(int idx);
	bool loadPages(int idx);
	void allocDiskPages(int pages);
	mempag::DiskPage getPage(int pageIdx);
	int *pageIndex;
	bool hasNext();
	int extractMin();
	std::string getDestination(int generation, int page);
	int generation;
	std::string originalFile;
	int page;
	int pages;
	int ways;
	mempag::DiskPage *diskPages;
	mempag::HeapPaging buffer;
	std::ofstream output;
};

} /* namespace diskpag */
#endif /* DISKPAGEMANAGER_H_ */
