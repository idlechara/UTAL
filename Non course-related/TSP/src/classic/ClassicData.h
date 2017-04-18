/*
 * ClassicData.h
 *
 *  Created on: Aug 30, 2013
 *      Author: kynku
 */

#ifndef CLASSICDATA_H_
#define CLASSICDATA_H_

#include <climits>
#include <string>
#include "../problem/Edge.h"
#include "../problem/Customer.h"
#include "../problem/Route.h"
#include <vector>

namespace Classic {

class ClassicData {
public:
	static const int ERROR = INT_MIN;
	ClassicData();
	ClassicData( int nNodes, int nTrucks, long truckCapacity, std::string name );
	virtual ~ClassicData();

	int getNNodes();
	void setNNodes(int nNodes);
	int getNTrucks();
	void setNTrucks(int nTrucks);
	std::string getName();
	void setName(std::string name);
	long getTruckCapacity();
	void setTruckCapacity(long truckCapacity);
	Problem::Customer getCustomer( int i );
	void setDistance( Problem::Edge edge, long distance );
	long getDistance( Problem::Customer c1, Problem::Customer c2 );
	std::string toString();
	bool isValid();
	long evaluateRoute( Problem::Route route );
	long evaluateRoutes( std::vector<Problem::Route> route );
	long getMaxDemandExcludingCustomer(int id);

private:
	long **distances;
	long truckCapacity;
	Problem::Customer *customers;
	std::string name;
	int nNodes;
	int nTrucks;
	void initialize();
};

} /* namespace Classic */
#endif /* CLASSICDATA_H_ */
