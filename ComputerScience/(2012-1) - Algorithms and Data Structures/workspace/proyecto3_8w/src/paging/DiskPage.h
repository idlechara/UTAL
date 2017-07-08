/*
 * DiskPage.h
 *
 *  Created on: Jul 9, 2012
 *      Author: kynku
 */

#ifndef DISKPAGE_H_
#define DISKPAGE_H_

#include <string>
#include <sstream>
#include <iostream>
#include <fstream>
#include <cstdlib>

namespace mempag {

class DiskPage {
public:
	int readNext();
	bool hasNext();
	DiskPage(std::string file, int generation, int page);
	virtual ~DiskPage();

private:
	std::stringstream targetFile;
	std::string originalFile;
	int buffer;
	int page;
	int generation;
	std::ifstream inputStream;
};

} /* namespace diskpag */
#endif /* DISKPAGE_H_ */
