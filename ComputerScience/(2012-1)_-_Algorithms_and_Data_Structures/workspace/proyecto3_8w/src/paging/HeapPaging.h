/*
 * HeapPaging.h
 *
 *  Created on: Jul 9, 2012
 *      Author: kynku
 */

#ifndef HEAPPAGING_H_
#define HEAPPAGING_H_

#include "Paging.h"
#include "../structs/structs.h"
#include <fstream>

namespace mempag {

class HeapPaging: public mempag::Paging {
public:
	HeapPaging(int bufferSize);
	HeapPaging();
	void push(t_pageValue target) ;
	void push(int target) ;
	virtual ~HeapPaging();
	t_pageValue extractMin();
	void percolateDown(int index);
	void dump(std::ofstream *channel);
	t_pageValue getMin();
	t_pageValue get(int index);
	void set(t_pageValue target, int index);
private:
	t_pageValue *memory;
};

} /* namespace mempag */
#endif /* HEAPPAGING_H_ */
