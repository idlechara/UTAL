/*
 * ClassicParser.cpp
 *
 *  Created on: Aug 8, 2013
 *      Author: kynku
 */

#include "ClassicParser.h"
#include <fstream>
#include <iostream>
#include <stdlib.h>
#include <vector>
#include <boost/algorithm/string.hpp>

ClassicParser::ClassicParser() {
	// TODO Auto-generated constructor stub

}

ClassicParser::~ClassicParser() {
	// TODO Auto-generated destructor stub
}

ClassicData ClassicParser::read(std::string filename){
	//loads file onto ram
	std::vector<std::string> lines;
	lines.clear();
	int linecount = 0 ;
	std::string line ;
	std::ifstream infile( filename.c_str() ) ;
	if ( infile ) {
		while ( getline( infile , line ) ) {
			lines.push_back(line);
		}
	}
	infile.close();

	//selection
	unsigned int pointer = 0;
	int dimension = 0;
	for(;pointer < lines.size(); pointer++){
		if(lines.at(pointer).find("NAME : ") != std::string::npos){
			std::cout << "Nombre: " <<lines.at(pointer).substr(7) << "\n";
			continue;
		}
		if(lines.at(pointer).find("COMMENT : ") != std::string::npos){
			std::cout << "Comentario: " <<lines.at(pointer).substr(10) << "\n";
			continue;
		}
		if(lines.at(pointer).find("DIMENSION : ") != std::string::npos){
			std::cout << "Tamaño: " << atoi(lines.at(pointer).substr(12).c_str()) << "\n";
			dimension = atoi(lines.at(pointer).substr(12).c_str());
			continue;
		}
		if(lines.at(pointer).find("CAPACITY : ") != std::string::npos){
			std::cout << "Capacidad: " << atoi(lines.at(pointer).substr(11).c_str()) << "\n";
			continue;
		}
		if(lines.at(pointer).find("NODE_COORD_SECTION") != std::string::npos){
			std::cout << "Revisando coordenadas: \n";
			for(int i=1; i<=dimension; i++){
				std::vector<std::string> strs;
				std::string toSplit = lines.at(pointer+i);
				boost::trim(toSplit);

				boost::split(strs, toSplit , boost::is_any_of(" "));
				std::cout << " id: " << strs.at(0)<< " x: " << strs.at(1)<< " y: " << strs.at(2) << "\n";
			}
			continue;
		}
		if(lines.at(pointer).find("DEMAND_SECTION") != std::string::npos){
			std::cout << "Revisando demandas: \n";
			for(int i=1; i<=dimension; i++){
				std::vector<std::string> strs;
				std::string toSplit = lines.at(pointer+i);
				boost::trim(toSplit);

				boost::split(strs, toSplit , boost::is_any_of(" "));
				std::cout << " id: " << strs.at(0)<< " demanda: " << strs.at(1) << "\n";
			}
			continue;
		}
		if(lines.at(pointer).find("TYPE : ") != std::string::npos){
			std::string substr = lines.at(pointer).substr(7);
				if (substr == "TSP")
					std::cout << "Tipo: TSP\n";
				if (substr == "ATSP")
					std::cout << "Tipo: ATSP\n";
				if (substr == "SOP")
					std::cout << "Tipo: SOP\n";
				if (substr == "HCP")
					std::cout << "Tipo: HCP\n";
				if (substr == "CVRP")
					std::cout << "Tipo: CVRP\n";
				if (substr == "TOUR")
					std::cout << "Tipo: TOUR\n";
			continue;
		}
	}

	return ClassicData();
}
