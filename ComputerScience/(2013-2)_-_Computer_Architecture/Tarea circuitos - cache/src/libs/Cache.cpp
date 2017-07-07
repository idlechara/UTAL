/*
 * Cache.cpp
 *
 *  Created on: Nov 21, 2013
 *      Author: jvarred
 */

#include "Cache.h"
#include <malloc.h>
#include <cmath>
#include <iostream>
#include <sstream>
#include <string>
#include <bitset>
namespace structs {

Cache::Cache() {
	// TODO Auto-generated constructor stub

}

Cache::Cache(int blockSize, int cacheSize) {
	/*
	 * tamaño de bloque: cuanto pesa en bits el numero
	 * tamaño de la caché, cuantos bloques tiene
	 */
	unsigned int linesInCache = cacheSize / blockSize;
	this->offsetSize = (unsigned long) (log2(blockSize) + 2);
	this->lineSize = (unsigned long) log2(linesInCache);
	this->tagSize = (unsigned long) (32 - lineSize - offsetSize);

	std::cout << "---------- STATS ----------" << std::endl;
	std::cout << "offset: " << this->offsetSize << std::endl;
	std::cout << "line: " << this->lineSize << std::endl;
	std::cout << "tag: " << this->tagSize << std::endl;
	std::cout << "------------------------------" << std::endl;

	if (isSplit) {
		this->lines = cacheSize/2;
		this->cacheLines = (cacheBlock_t *) malloc(
				sizeof(cacheBlock_t) * this->lines);
		this->instrCacheLines= (cacheBlock_t *) malloc(
				sizeof(cacheBlock_t) * this->lines);

		for (unsigned int i = 0; i < this->lines; i++) {
			this->cacheLines[i].v = false;
			this->cacheLines[i].dirty = false;
			this->instrCacheLines[i].v = false;
			this->instrCacheLines[i].dirty = false;
		}

	} else {
		this->lines = cacheSize;
		this->cacheLines = (cacheBlock_t *) malloc(
				sizeof(cacheBlock_t) * this->lines);

		for (unsigned int i = 0; i < this->lines; i++) {
			this->cacheLines[i].v = false;
			this->cacheLines[i].dirty = false;
		}
	}

	this->instr_stats = this->data_stats = this->instr_faults_stats =
			this->data_faults_stats = this->copy_to_lower_stats =
					this->copy_to_cache_stats = 0;
	this->isWriteThough = this->isFullyAssociative = this->isSetAsociative =
			this->isWriteNoAllocate = this->isSplit = false;
}

Cache::~Cache() {
}

void Cache::readDataFromLowerMemory(std::string toDebug) {

	this->cacheLines[this->getLine(toDebug)].v = true;
	this->cacheLines[this->getLine(toDebug)].tag = this->getTag(toDebug);

}

void Cache::readInstructionFromLowerMemory(std::string toDebug) {

	this->instrCacheLines[this->getLine(toDebug)].v = true;
	this->instrCacheLines[this->getLine(toDebug)].tag = this->getTag(toDebug);

}


bool Cache::isDirty(std::string toDebug) {

	return this->cacheLines[this->getLine(toDebug)].dirty;

}

void Cache::setDirty(std::string toDebug, bool is) {

	this->cacheLines[this->getLine(toDebug)].dirty = is;

}

void Cache::read(std::string toDebug) {

	if (checkHit(toDebug) == true) {
		//Return data
		data_stats++;
		return;
	}

	//If the data isn't in the cache
	else {
		data_faults_stats++;
		//Locate cache block to use

		//if WriteThough policy is enabled...
		if (!this->isWriteThough) {

			//Check if is dirty
			if (this->isDirty(toDebug)) {
				//Write previous data to the lower memory
				copy_to_lower_stats++;
			}
			//Read data from lower memory and load into the cache
			copy_to_cache_stats++;
			this->readDataFromLowerMemory(toDebug);

			//Mark cache block as not dirty
			this->setDirty(toDebug, false);

		}

		//if WriteThough policy isn't enabled
		else {
			copy_to_cache_stats++;
			//Read data from lower memory and load into the cache
			this->readDataFromLowerMemory(toDebug);
		}
	}

	//Return data
	return;

}

void Cache::execute(std::string toDebug) {

	if (checkHit(toDebug) == true) {
		//Return data
		instr_stats++;
		return;
	}

	//If the data isn't in the cache
	else {
		instr_faults_stats++;
		//Locate cache block to use

		//if WriteThough policy is enabled...
		if (!this->isWriteThough) {

			//Check if is dirty
			if (this->isDirty(toDebug)) {
				//Write previous data to the lower memory
				copy_to_lower_stats++;
			}
			//Read data from lower memory and load into the cache
			copy_to_cache_stats++;
			this->readInstructionFromLowerMemory(toDebug);

			//Mark cache block as not dirty
			this->setDirty(toDebug, false);

		}

		//if WriteThough policy isn't enabled
		else {
			copy_to_cache_stats++;
			//Read data from lower memory and load into the cache
			this->readInstructionFromLowerMemory(toDebug);
		}
	}

	//Return data
	return;

}

void Cache::write(std::string toDebug) {

	if (checkHit(toDebug) == true) {
		if (isWriteThough) {
			data_stats++;
			//Write data into cache block
		}
	}

	//If data isn't in the cache
	else {
		data_faults_stats++;
		//Locate cache block to use
		if (isDirty(toDebug)) {
			copy_to_lower_stats++;
			//Write data to lower memory
		}
		copy_to_cache_stats++;
		//Read data from lower memory to cache
		this->readDataFromLowerMemory(toDebug);
	}

	if (isWriteThough) {
		copy_to_lower_stats++;
		//Write into lower memory

	} else {
		//Write new data into cache block

		//Mark as dirty
		this->setDirty(toDebug, false);
	}

}

unsigned long Cache::getTag(std::string toDebug) {
	unsigned long x;
	std::stringstream ss;
	ss << std::hex << toDebug;
	ss >> x;

	std::bitset<32> data(x);
	std::bitset<32> tag = (data >> (32 - this->tagSize));
	return tag.to_ulong();
}

unsigned long Cache::getLine(std::string toDebug) {
	unsigned long x;
	std::stringstream ss;
	ss << std::hex << toDebug;
	ss >> x;

	std::bitset<32> data(x);

	std::bitset<32> word = ((data << this->tagSize)
			>> (this->tagSize + this->offsetSize + 2));
	return word.to_ulong();
}

bool Cache::checkHit(std::string toDebug) {
	unsigned long x;
	std::stringstream ss;
	ss << std::hex << toDebug;
	ss >> x;

	std::bitset<32> data(x);

	if (this->cacheLines[this->getLine(toDebug)].v == true
			&& this->cacheLines[this->getLine(toDebug)].tag
					== this->getTag(toDebug)) {
//		std::cout << "Hit! \tTag:" << this->getTag(toDebug) << " \tData:"
//				<< std::hex << data.to_ulong() << std::dec << " \tLine:"
//				<< std::hex << this->getLine(toDebug) << std::dec;
		return true;
	} else {
//		std::cout << "Miss! \tTag:" << this->getTag(toDebug) << " \tData:"
//				<< std::hex << data.to_ulong() << std::dec << " \tLine:"
//				<< std::hex << this->getLine(toDebug) << std::dec;
		return false;
	}

}

bool Cache::checkHitOnInstruction(std::string toDebug) {
	unsigned long x;
	std::stringstream ss;
	ss << std::hex << toDebug;
	ss >> x;

	std::bitset<32> data(x);

	if (this->instrCacheLines[this->getLine(toDebug)].v == true
			&& this->instrCacheLines[this->getLine(toDebug)].tag
					== this->getTag(toDebug)) {
//		std::cout << "Hit! \tTag:" << this->getTag(toDebug) << " \tData:"
//				<< std::hex << data.to_ulong() << std::dec << " \tLine:"
//				<< std::hex << this->getLine(toDebug) << std::dec;
		return true;
	} else {
//		std::cout << "Miss! \tTag:" << this->getTag(toDebug) << " \tData:"
//				<< std::hex << data.to_ulong() << std::dec << " \tLine:"
//				<< std::hex << this->getLine(toDebug) << std::dec;
		return false;
	}

}

int Cache::getBlockSize() const {
	return blockSize;
}

void Cache::setBlockSize(int blockSize) {
	this->blockSize = blockSize;
}

bool Cache::isIsFullyAssociative() const {
	return isFullyAssociative;
}

void Cache::setIsFullyAssociative(bool isFullyAssociative) {
	this->isFullyAssociative = isFullyAssociative;
}

bool Cache::isIsSetAsociative() const {
	return isSetAsociative;
}

void Cache::setIsSetAsociative(bool isSetAsociative) {
	this->isSetAsociative = isSetAsociative;
}

bool Cache::isIsSplit() const {
	return isSplit;
}

void Cache::setIsSplit(bool isSplit) {
	this->isSplit = isSplit;
}

bool Cache::isIsWriteNoAllocate() const {
	return isWriteNoAllocate;
}

void Cache::setIsWriteNoAllocate(bool isWriteNoAllocate) {
	this->isWriteNoAllocate = isWriteNoAllocate;
}

bool Cache::isIsWriteThough() const {
	return isWriteThough;
}

void Cache::setIsWriteThough(bool isWriteThough) {
	this->isWriteThough = isWriteThough;
}

int Cache::getLines() const {
	return lines;
}

void Cache::setLines(int lines) {
	this->lines = lines;
}

int Cache::getSetSize() const {
	return setSize;
}

void Cache::setSetSize(int setSize) {
	this->setSize = setSize;
}

cacheBlock* Cache::getCacheLines() const {
	return cacheLines;
}

void Cache::setCacheLines(cacheBlock* cacheLines) {
	this->cacheLines = cacheLines;
}

int Cache::getLineSize() const {
	return lineSize;
}

void Cache::setLineSize(int lineSize) {
	this->lineSize = lineSize;
}

int Cache::getOffsetSize() const {
	return offsetSize;
}

void Cache::setOffsetSize(int offsetSize) {
	this->offsetSize = offsetSize;
}

int Cache::getTagSize() const {
	return tagSize;
}

void Cache::setTagSize(int tagSize) {
	this->tagSize = tagSize;
}

unsigned long Cache::getCacheIdx() const {
	return cacheIdx;
}

void Cache::setCacheIdx(unsigned long cacheIdx) {
	this->cacheIdx = cacheIdx;
}

unsigned long Cache::getDataFaultsStats() const {
	return data_faults_stats;
}

void Cache::setDataFaultsStats(unsigned long dataFaultsStats) {
	data_faults_stats = dataFaultsStats;
}

unsigned long Cache::getDataStats() const {
	return data_stats;
}

void Cache::setDataStats(unsigned long dataStats) {
	data_stats = dataStats;
}

} /* namespace structs */
