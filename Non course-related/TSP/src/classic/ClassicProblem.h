/*
 * ClassicProblem.h
 *
 *  Created on: Sep 5, 2013
 *      Author: kynku
 */

#ifndef CLASSICPROBLEM_H_
#define CLASSICPROBLEM_H_

#include <ilcplex/ilocplexi.h>
#include <boost/unordered_map.hpp>
#include "ClassicData.h"
#include "../problem/Route.h"

namespace Classic {

class ClassicProblem {
public:
	ClassicProblem(ClassicData data);
	virtual ~ClassicProblem();
	ClassicData getData();
	int getDimension();
	long getDemand(int i);
	long getTruckCapacity();
	int getNTrucks();
	long getDistance(int i, int j);
	Problem::Customer getCustomer(int i);
	long getObjValue();
	bool isSolved();
	void setSolved(bool solved);
	bool isAbortedProcess();	//TODO syncronized method
	int** makeRouteInfo(IloCplex cplex, IloIntVar **x);
	int** makeRouteInfoToMTZ( IloCplex cplex, IloIntVar **x );
	std::vector<Problem::Route> makeRoutes( int **routeInfoParam );
	bool addConstraints( IloCplex cplex, IloIntVar **x, int **routeInfo, std::vector<Problem::Route> routes); //throws DecimalPrecitionException
	int** solveWithBC();
	int** solveWithTighteningMTZ();
	static IloCplex getCplex();



private:
	static IloCplex staticCplex;
	ClassicData data;
	long cost;
	bool solved;
	bool **solution;
	bool abortProcess;
	boost::unordered_map<std::string,std::string> constrainstsAdded;	//TOdo learn how to use this
	void setData(ClassicData data);
};

} /* namespace Problem */
#endif /* CLASSICPROBLEM_H_ */
