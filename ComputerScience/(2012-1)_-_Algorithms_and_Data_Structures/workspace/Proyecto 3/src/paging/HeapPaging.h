/*
 * HeapPaging.h
 *
 *  Created on: Jul 9, 2012
 *      Author: kynku
 */

#ifndef HEAPPAGING_H_
#define HEAPPAGING_H_

#include "Paging.h"
#include "constants.h"
#include <fstream>

namespace mempag {

class HeapPaging: public mempag::Paging {
public:
	HeapPaging(int bufferSize);
	HeapPaging();
	void push(t_value target) ;
	virtual ~HeapPaging();
	t_value extractMin();
	void percolateDown(int index);
	void dump();
	void dump(std::ofstream channel);
	t_value getMin();
	t_value get(int index);
	void set(t_value target, int index);
private:
	t_value *memory;
};

} /* namespace mempag */
#endif /* HEAPPAGING_H_ */
