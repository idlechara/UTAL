/*
 * Cache.h
 *
 *  Created on: Nov 21, 2013
 *      Author: jvarred
 */

#ifndef CACHE_H_
#define CACHE_H_

#include "string"

namespace structs {

typedef struct cacheBlock{
	bool v;
	bool dirty;
	unsigned long tag;
	/*
	 * this data value is unused
	 */
	//int data;
}cacheBlock_t;

class Cache {
public:
	/*
	 * Con/Des-structors
	 */
	Cache();
	Cache(int blockSize, int cacheSize);
	virtual ~Cache();

	/*
	 * Setters & getters
	 */
	int getBlockSize() const;
	void setBlockSize(int blockSize);
	bool isIsFullyAssociative() const;
	void setIsFullyAssociative(bool isFullyAssociative);
	bool isIsSetAsociative() const;
	void setIsSetAsociative(bool isSetAsociative);
	bool isIsSplit() const;
	void setIsSplit(bool isSplit);
	bool isIsWriteNoAllocate() const;
	void setIsWriteNoAllocate(bool isWriteNoAllocate);
	bool isIsWriteThough() const;
	void setIsWriteThough(bool isWriteThough);
	int getLines() const;
	void setLines(int lines);
	int getSetSize() const;
	void setSetSize(int setSize);
	cacheBlock* getCacheLines() const;
	void setCacheLines(cacheBlock* cacheLines);
	int getLineSize() const;
	void setLineSize(int lineSize);
	int getOffsetSize() const;
	void setOffsetSize(int offsetSize);
	int getTagSize() const;
	void setTagSize(int tagSize);

	void setDirty(std::string toDebug, bool is);
	bool isDirty(std::string toDebug);
	void toDebug(std::string toDebug);
	void read(std::string toDebug);
	void execute(std::string toDebug);
	void write(std::string toDebug);
	bool checkHitOnInstruction(std::string toDebug);
	bool checkHit(std::string toDebug);
	void readInstructionFromLowerMemory(std::string toDebug);
	void readDataFromLowerMemory(std::string toDebug);
	unsigned long getLine(std::string toDebug);
	std::string getHex(std::string toDebug);

	unsigned long getTag(std::string toDebug);
	unsigned long getCacheIdx() const;
	void setCacheIdx(unsigned long cacheIdx);
	unsigned long getDataFaultsStats() const;
	void setDataFaultsStats(unsigned long dataFaultsStats);
	unsigned long getDataStats() const;
	void setDataStats(unsigned long dataStats);
	unsigned long getInstrFaultsStats() const;
	void setInstrFaultsStats(unsigned long instrFaultsStats);
	unsigned long getInstrStats() const;
	void setInstrStats(unsigned long instrStats);
	unsigned long getWordsCopiedFromStats() const;
	void setWordsCopiedFromStats(unsigned long wordsCopiedFromStats);
	unsigned long getWordsCopiedToStats() const;
	void setWordsCopiedToStats(unsigned long wordsCopiedToStats);

	/*
	 *
	 */
	unsigned long instr_stats, data_stats, instr_faults_stats, data_faults_stats, copy_to_lower_stats, copy_to_cache_stats;

private:
	cacheBlock *cacheLines, *instrCacheLines;
	unsigned long blockSize, lines, setSize;
	unsigned long offsetSize, lineSize, tagSize;
	unsigned long cacheIdx;

	bool isWriteThough, isFullyAssociative, isSetAsociative, isWriteNoAllocate, isSplit;
};

} /* namespace structs */

#endif /* CACHE_H_ */
