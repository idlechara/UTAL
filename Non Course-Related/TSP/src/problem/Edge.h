/*
 * Edge.h
 *
 *  Created on: Aug 30, 2013
 *      Author: kynku
 */

#ifndef EDGE_H_
#define EDGE_H_

#include "Customer.h"

namespace Problem {

class Edge {
public:
	Edge();
	Edge(Problem::Customer initialCustomer, Problem::Customer finalCustomer);
	virtual ~Edge();

	Customer getFinalCustomer();
	void setFinalCustomer(Customer finalCustomer);
	Customer getInitialCustomer();
	void setInitialCustomer(Customer initialCustomer);
	bool equals(Edge obj);
	int hashCode();
	std::string toString();
	int compareTo(Edge edge);

private:
	Customer initialCustomer;
	Customer finalCustomer;

};

} /* namespace Problem */
#endif /* EDGE_H_ */
