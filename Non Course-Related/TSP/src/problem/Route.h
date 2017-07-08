/*
 * Route.h
 *
 *  Created on: Aug 30, 2013
 *      Author: kynku
 */

#ifndef ROUTE_H_
#define ROUTE_H_

#include <vector>
#include "Customer.h"
#include <string>
namespace Problem {

class Route {
public:
	Route(long maxCapacity);
	Route(long maxCapacity, bool violateCapatityConstraints);
	virtual ~Route();
	long getActualCapacity();
	void setActualCapacity(long actualCapacity);
	long getMaxCapacity();
	void setMaxCapacity(long maxCapacity);
	bool canViolateCapacityConstraints();
	void setViolateCapacityConstraints(bool isTabuRoute);
	bool add(Problem::Customer customer);
	bool add(Problem::Customer customer /*, Random  random*/);
	bool add(int index, Problem::Customer customer);
	bool canAdd(Problem::Customer customer);
	Problem::Customer remove(int index);
	bool remove(Problem::Customer);
	bool set(int index, Problem::Customer newCustomer);
	int size();
	int indexOf(Problem::Customer customer);
	Problem::Customer get(int index);
	bool contains(Problem::Customer customer);
	std::string toString();
	std::string toText();
	Problem::Route clone();
	bool equals(Problem::Route obj);
	int* toArray();
	long getTotalDemand();

private:
	std::vector<Problem::Customer> customers;
	long maxCapatity;
	long actualCapacity;
	bool violateCapacityContraints;
};

} /* namespace Problem */
#endif /* ROUTE_H_ */
