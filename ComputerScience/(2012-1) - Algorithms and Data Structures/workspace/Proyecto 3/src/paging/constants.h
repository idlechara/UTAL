/*
 * constants.h
 *
 *  Created on: Jul 9, 2012
 *      Author: kynku
 */

#ifndef CONSTANTS_H_
#define CONSTANTS_H_

namespace constants{
	const int bufferMaxSize = 65536;
}

typedef struct t_value{
	int page;
	int value;
} t_value;

#endif /* CONSTANTS_H_ */
