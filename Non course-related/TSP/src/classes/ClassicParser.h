/*
 * ClassicParser.h
 *
 *  Created on: Aug 8, 2013
 *      Author: kynku
 */

#ifndef CLASSICPARSER_H_
#define CLASSICPARSER_H_
#include "ClassicData.h"
#include <string>

class ClassicParser {
public:
	static ClassicData read(std::string filename);
	ClassicParser();
	virtual ~ClassicParser();
};

#endif /* CLASSICPARSER_H_ */
