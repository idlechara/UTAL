/*
 * Customer.h
 *
 *  Created on: Aug 30, 2013
 *      Author: kynku
 */

#ifndef CUSTOMER_H_
#define CUSTOMER_H_

#include <string>

namespace Problem {

class Customer {
public:
	Customer();
	virtual ~Customer();

	//metodos
	long getDemand();
	void setDemand(long demand);
	int getId();
	void setid(int id);
	int compareTo(Customer customer);

private:
	int id;
	long demand;

};

} /* namespace Problem */
#endif /* CUSTOMER_H_ */
