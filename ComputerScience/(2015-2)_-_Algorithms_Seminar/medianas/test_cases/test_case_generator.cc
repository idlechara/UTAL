#include <bits/stdc++.h>
#include <iostream>
#include <fstream>

using namespace std;


void make_random_testfile(size_t n){
    unsigned seed = chrono::system_clock::now().time_since_epoch().count();
    std::minstd_rand0 random_generator(seed);
	ofstream output;
	output.open(std::to_string(n)+".random");
	for(size_t i = 0; i < n; ++i)
		output << random_generator() << "\n";
	output.close();
	return;
}

void make_sorted_testfile(size_t n){
	vector<long> array;
	string line;
	ifstream input (std::to_string(n)+".random");
	if (input.is_open()){
		while ( getline (input,line) ){
		  array.push_back(stoi(line));
	  	}
		input.close();
	}
	else{
		cout << "Unable to open file"; 
		return;
	}
	
	std::sort(array.begin(), array.end(), std::greater<long>());
	
	ofstream output;
	output.open(std::to_string(n)+".desorted");
	for(size_t i = 0; i < n; ++i)
		output << array[i] << "\n";
	output.close();
	
	std::sort(array.begin(), array.end());

	output.open(std::to_string(n)+".sorted");
	for(size_t i = 0; i < n; ++i)
		output << array[i] << "\n";
	output.close();
	
	return;
}


int main (int argc, char const *argv[]){
	size_t max_power = (size_t)std::stol(argv[2]);
	size_t min_power = (size_t)std::stol(argv[1]);
	
	for(size_t i = min_power; i < max_power; ++i)
	{
		std::cout << "Building x10^" << i <<  " series... ";
		for(size_t j = 1; j < 10; j++)
		{
			long value = j * pow(10,i);
			make_random_testfile(value);
			make_sorted_testfile(value);
		}

		std::cout << "done.\n";
	}
	return 0;
}