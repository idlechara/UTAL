/*
 * ClassicData.h
 *
 *  Created on: Aug 8, 2013
 *      Author: kynku
 */

#ifndef CLASSICDATA_H_
#define CLASSICDATA_H_

class ClassicData {
public:
	int size;
	int *demand;
	int **distance;
	ClassicData();
	virtual ~ClassicData();
};

#endif /* CLASSICDATA_H_ */
