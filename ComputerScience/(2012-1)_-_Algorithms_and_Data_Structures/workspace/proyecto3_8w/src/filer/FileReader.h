/*
 * FileReader.h
 *
 *  Created on: Jul 12, 2012
 *      Author: kynku
 */

#ifndef FILEREADER_H_
#define FILEREADER_H_
#include "fstream"

namespace diskpag {

class FileReader {
public:
	bool hasData();
	int nextInt();
	FileReader(std::string filename, int generation, int page);
	virtual ~FileReader();
private:
	std::ifstream fileInput;
};

} /* namespace diskpag */
#endif /* FILEREADER_H_ */
