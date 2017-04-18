/*
 * Block.h
 *
 *  Created on: Nov 20, 2013
 *      Author: jvarred
 */

#ifndef BLOCK_H_
#define BLOCK_H_

namespace structs {

class Block {
public:
	Block();
	Block(int words);
	virtual ~Block();
	void getWordAt(int index);
private:

};

} /* namespace structs */

#endif /* BLOCK_H_ */
