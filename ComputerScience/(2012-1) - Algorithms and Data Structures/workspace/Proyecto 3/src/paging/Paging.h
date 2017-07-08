/*
 * Paging.h
 *
 *  Created on: Jul 5, 2012
 *      Author: kynku
 */

#include <iostream>

#ifndef PAGING_H_
#define PAGING_H_

namespace mempag {

class Paging {

public:
	int get(int index);
	void push(int target);
	int size();
	bool isEmpty();
	bool isFull();
	void clear();
	void set(int target, int index);
	void resetIterator();
	int iterate();
	bool writePage(std::string target);
	//(int *) getMemoryAdress();
	Paging(int bufSize);
	Paging();
	virtual ~Paging();
	//end of public domain

protected:
	int iteratorPos;
	int *memory;
	int actualSize;
	int bufferSize;
};

} /* namespace mempag */
#endif /* PAGING_H_ */
