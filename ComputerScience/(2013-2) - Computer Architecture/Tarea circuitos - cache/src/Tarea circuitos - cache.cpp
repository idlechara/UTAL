//============================================================================
// Name        : Tarea.cpp
// Author      : Kynku
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
#include <boost/algorithm/string.hpp>
#include <boost/program_options.hpp>
#include <iostream>
#include <bitset>
#include <string>
#include <sstream>
#include <fstream>
#include <vector>
#include "libs/Cache.h"
namespace {
const size_t ERROR_IN_COMMAND_LINE = 1;
const size_t SUCCESS = 0;
const size_t ERROR_UNHANDLED_EXCEPTION = 2;

} // namespace

int main(int argc, char** argv) {

//	//TODO argument parsing
	namespace po = boost::program_options;

	po::variables_map vm;
	po::options_description desc("Allowed options");

	desc.add_options()("bs", po::value<int>(), "block size")("cs",
			po::value<int>(), "cache size")("fa",
			"set fully-associative policy")("sa", po::value<int>(),
			"set set-associative policy")("wt", "set write-though")("wna",
			"set write-no-allocate")("split",
			"set split cache for instructions and data");

//	po::options_description hidden("Hidden options");
//	hidden.add_options()("input-file", po::value<std::vector<std::string> >(),
//			"input file");

	po::positional_options_description positionalOptions;
	positionalOptions.add("input-files", -1);


	try {
		po::store(
				po::command_line_parser(argc, argv).options(desc).allow_unregistered().positional(
						positionalOptions).run(), vm); // throws on error

		/** --help option
		 */


		po::notify(vm); // throws on error, so do after help in case
						// there are any problems
	} catch (boost::program_options::required_option& e) {
//	      rad::OptionPrinter::formatRequiredOptionError(e);
		std::cerr << "ERROR: " << e.what() << std::endl << std::endl;
//	      rad::OptionPrinter::printStandardAppDesc(appName,
//	                                               std::cout,
//	                                               desc,
//	                                               &positionalOptions);
		return ERROR_IN_COMMAND_LINE;
	} catch (boost::program_options::error& e) {
		std::cerr << "ERROR: " << e.what() << std::endl << std::endl;
//	      rad::OptionPrinter::printStandardAppDesc(appName,
//	                                               std::cout,
//	                                               desc,
//	                                               &positionalOptions);
		return ERROR_IN_COMMAND_LINE;
	}

//	po::store(po::parse_command_line(argc, argv, desc), vm);
//	po::store(po::parse_command_line(argc, argv, hidden), vm);



	if(vm.count("input-files")){
	    std::vector<std::string> files = vm["input-files"].as<std::vector<std::string> >();
	    for(std::string file : files){
	        std::cout << "Input file " << file << std::endl;
	    }
	}


	unsigned int cacheSize = 3, blockSize = 3;

	if (vm.count("bs")) {
		blockSize = (unsigned int) vm["bs"].as<int>();
	}

	if (vm.count("cs")) {
		cacheSize = (unsigned int) vm["cs"].as<int>();
	}

	if (cacheSize == 3 || blockSize == 3) {
		std::cout << "ERROR!" << std::endl;
		return 1;
	}

	structs::Cache cache(16, 8192);
	cache.setIsWriteThough(false);
	cache.setIsWriteNoAllocate(false);

	if (vm.count("wt")) {
		cache.setIsWriteThough(true);
	}

	if (vm.count("wna")) {
		cache.setIsWriteNoAllocate(true);
	}

	if (vm.count("split")) {
		cache.setIsSplit(true);
	}

	if (vm.count("fa") || vm.count("sa")) {
		std::cout << "ERROR! Not implemented." << std::endl;
		return 1;
	}


//	std::cout << "Input file: "
//			<< (vm["file"].as<std::vector<std::string> >())[0]
//			<< std::endl;
//	return 0;
//
//	cache.toDebug("2fd94123");
//	cache.checkHit("2fd94123");
//	cache.read("2fd94123");
//	cache.read("2fd94123");

	std::string line;
	std::ifstream myfile(
			(vm["input-file"].as<std::vector<std::string> >())[0].c_str());
	int t = 0;
	if (myfile.is_open()) {
		while (getline(myfile, line)) {
//			cout << (t) << ": ";
			std::vector<std::string> words;
			boost::split(words, line, boost::is_any_of(", "),
					boost::token_compress_on);
			if (words[0] == std::string("0")) {
				cache.read(words[1]);
//				cout << "READ";
			} else if (words[0] == std::string("1")) {
				cache.write(words[1]);
//				cout << "WRITE";
			} else {
				cache.execute(words[1]);
//				cout << "EXECUTE";
			}
//			cout << endl;
			t++;
		}
		myfile.close();

		std::cout << "Instruction references:        \t" << cache.instr_stats
				<< std::endl;
		std::cout << "Data references:               \t" << cache.data_stats
				<< std::endl;
		std::cout << "Instruction references misses: \t"
				<< cache.instr_faults_stats << std::endl;
		std::cout << "Data references misses:        \t"
				<< cache.data_faults_stats << std::endl;
		std::cout << "Words fetched from memory:     \t"
				<< cache.copy_to_cache_stats << std::endl;
		std::cout << "Words copied back to memory:   \t"
				<< cache.copy_to_lower_stats << std::endl;

	}

	else
		std::cout << "Unable to open file";

	return 0;
}
