#include <bits/stdc++.h>
#include <iostream>
#include <fstream>

using namespace std;

std::deque<long> load( long n, std::string extension){
	std::deque<long> data;
	std::string line;
	std::ifstream input("test_cases/" + std::to_string(n) + "." + extension);

	if (input.is_open()){
		while ( getline (input,line) ){
		  data.push_back(stoi(line));
	  	}
		input.close();
	}
	else{
		std::cout << "Unable to open file";
		return data;
	}
	return data;
}

long median_of_medians(std::deque<long> &array, long i, long j, long k) {
	if(j-i < k)
	  return (i + j) / 2;
	std::deque<std::pair<long, long>> temp;
    for (long i_idx = i; i_idx <= j; i_idx += k) {
		long range = min(i_idx + k, j) - i_idx;
      std::sort( array.begin()+i_idx, array.begin()+i_idx+range);
      temp.push_back({array[i_idx + (range / 2)], i_idx + (range / 2)});
    }
    std::sort(temp.begin(), temp.end(),
              [](std::pair<long, long> a, std::pair<long, long> b) {
                return a.first < b.first;
              });
    return temp[temp.size() / 2].second;
}


long iterative_median_of_medians(std::deque<long> &array, long i, long j, long k) {
	if(j-i < k)
	  return (i + j) / 2 ;
	std::deque<std::pair<long, long>> temp;
    for (long i_idx = i; i_idx <= j; i_idx += k) {
		long range = min(i_idx + k, j) - i_idx;
      	std::sort( array.begin()+i_idx, array.begin()+i_idx+range );
      	temp.push_back({array[i_idx + (range / 2)], i_idx + (range / 2)});
    }
	std::deque<std::pair<long, long>> bubble;
	while(temp.size() > ceil(log2(k))*k){	//could it be 2k ??
	    for (long i_idx = 0; i_idx <= temp.size(); i_idx += k) {
			long range = min(i_idx + k, (long)temp.size()) - i_idx;
			
	      	std::sort( temp.begin()+i_idx, temp.begin()+i_idx+range ,
              [](std::pair<long, long> a, std::pair<long, long> b) {
                return a.first < b.first;
              });
			  
	      bubble.push_back(temp[i_idx + (range / 2)]);
	    }
		std::swap(bubble, temp);
		bubble.clear();	
	}
    return temp[temp.size() / 2].second;
}

long getFakeRandomPivot(long i, long j) { return (i); }

long partition(std::deque<long> &array, long pidx, long i, long j) {
  if (i == j) return i;
  std::swap(array[pidx], array[i]);
  long pivot = array[i]; long i_idx, j_idx; i_idx = i + 1; j_idx = j;
  while(true){
    while ((i_idx < j_idx) && (array[i_idx] < pivot)) i_idx++;
    while ((i_idx < j_idx) && (array[j_idx] >= pivot)) j_idx--;
    if (i_idx < j_idx) {
      std::swap(array[i_idx], array[j_idx]);
      i_idx++;
      j_idx--;
    } 
	else break;
  }
  if (array[i_idx] >= pivot)
    i_idx--;
  std::swap(array[i], array[i_idx]);
  return i_idx;
}

long quickselect_index(std::deque<long> &array, long i, long j, long target){
	long p_idx;
	while(i<j){
		p_idx = getFakeRandomPivot(i,j);
		p_idx = partition(array, p_idx, i, j);
		
		if(p_idx == target) return target;
		
		if(p_idx < target) i = p_idx+1;
		else j = p_idx-1;
	}
	return p_idx;
}


long introspective_quickselect_index(std::deque<long> &array, long i, long j, long target){
	long p_idx;
	bool introspect = false;
	long threshold = ceil(log2(j-i+1));
	long range;
	while(i<j && threshold > 0){
		range = j-i;
		p_idx = getFakeRandomPivot(i,j);
		p_idx = partition(array, p_idx, i, j);
		
		if(i+(range*0.7)>p_idx || i+(range*0.3)>p_idx )
			threshold--;
		
		if(p_idx == target) return target;
		
		if(p_idx < target) i = p_idx+1;
		else j = p_idx-1;
	}
	if(threshold <= 0){
		iterative_median_of_medians(array, i, j, 5); //could be log2(data)
	}
	else return p_idx;
}

long quickselect_value(std::deque<long> &array, long i, long j, long target){
	long p_idx = (i+j)/2;
	while(i<j){
		p_idx = (i+j)/2;
		p_idx = partition(array, p_idx, i, j);
		if(array[p_idx] < target)	i = p_idx+1;
		else j = p_idx-1;
	}
	return p_idx;
}

long averageCaseLinearMedian(std::deque<long> &array){
	return array[quickselect_index(array, 0, array.size(), array.size()/2)];
};
long worstCaseLinearMedian(std::deque<long> &array, long k=5){
	return array[median_of_medians(array, 0, array.size(), k)];
};
long iterativeLinearMedian(std::deque<long> &array, long k=5){
	return array[iterative_median_of_medians(array, 0, array.size(), k)];
};
long worstCaseIntrospectiveLinearMedian(std::deque<long> &array){
	return array[introspective_quickselect_index(array, 0, array.size(), array.size()/2)];
};

float count_less_or_equal(std::deque<long> &array, long target){
	float count = 0;
	for(size_t i = 0; i < array.size(); ++i)
		if(array[i] <= target) count++;
	count = abs((array.size()/2)-count)/array.size()/2;
	count*=100.0;
	count = 100.0 - count;
	return count;
}

void do_experiment(long n, std::string extension = "random"){

	std::deque<long> data_avg = load(n, extension);
	std::deque<long> data_wst = load(n, extension);
	std::deque<long> data_iws = load(n, extension);
	std::deque<long> data_iqs = load(n, extension);
	
	long wst_value, iws_value, iqs_value;
	
    typedef std::chrono::high_resolution_clock Clock;
    typedef std::chrono::microseconds microseconds;
	microseconds ms_avg;
	if(extension != "random" && n <= 40000){
		Clock::time_point avg_t0 = Clock::now();
		averageCaseLinearMedian(data_avg);
		Clock::time_point avg_t1 = Clock::now();
		ms_avg = std::chrono::duration_cast<microseconds>(avg_t1 - avg_t0);
	}
	else if(extension == "random"){
		Clock::time_point avg_t0 = Clock::now();
		averageCaseLinearMedian(data_avg);
		Clock::time_point avg_t1 = Clock::now();
		ms_avg = std::chrono::duration_cast<microseconds>(avg_t1 - avg_t0);
	}
	Clock::time_point wst_t0 = Clock::now();
	wst_value = worstCaseLinearMedian(data_wst);
	Clock::time_point wst_t1 = Clock::now();
	microseconds ms_wst = std::chrono::duration_cast<microseconds>(wst_t1 - wst_t0);

	Clock::time_point iws_t0 = Clock::now();
	iws_value = iterativeLinearMedian(data_iws);
	Clock::time_point iws_t1 = Clock::now();
	microseconds ms_iws = std::chrono::duration_cast<microseconds>(iws_t1 - iws_t0);

	Clock::time_point iqs_t0 = Clock::now();
	iqs_value = worstCaseIntrospectiveLinearMedian(data_iqs);
	Clock::time_point iqs_t1 = Clock::now();
	microseconds ms_iqs = std::chrono::duration_cast<microseconds>(iqs_t1 - iqs_t0);

	// std::cout << "values!: ()" << wst_value << ", " << iws_value << "\n";
	// wst_value = count_less_or_equal(data_wst, wst_value);
	// iws_value = count_less_or_equal(data_iws, iws_value);
	//
	// long wst_precision = (abs((n/2.0)-wst_value)/(n/2.0))*100.0;
	// long iws_precision = (abs((n/2.0)-iws_value)/(n/2.0))*100.0;
	// std::cout << "Results!: ()" << wst_value << ", " << iws_value << "\n";
	
	std::cout << n <<"\t"<< ms_avg.count() <<"\t"<< ms_wst.count() <<"\t"<< ms_iws.count() <<"\t"<< ms_iqs.count() <<"\n";//<<"\t"<< "100" << wst_precision <<"\t"<< wst_precision <<"\n";
}


void do_experiment_presicion(long n, std::string extension = "random"){

	std::deque<long> data_avg = load(n, extension);
	std::deque<long> data_wst = load(n, extension);
	std::deque<long> data_iws = load(n, extension);
	std::deque<long> data_iqs = load(n, extension);
	
	
	long wst_value, iws_value, iqs_value;
	
    typedef std::chrono::high_resolution_clock Clock;
    typedef std::chrono::microseconds microseconds;
	microseconds ms_avg;
	Clock::time_point wst_t0 = Clock::now();
	wst_value = worstCaseLinearMedian(data_wst);
	Clock::time_point wst_t1 = Clock::now();
	microseconds ms_wst = std::chrono::duration_cast<microseconds>(wst_t1 - wst_t0);

	Clock::time_point iws_t0 = Clock::now();
	iws_value = iterativeLinearMedian(data_iws);
	Clock::time_point iws_t1 = Clock::now();
	microseconds ms_iws = std::chrono::duration_cast<microseconds>(iws_t1 - iws_t0);

	Clock::time_point iqs_t0 = Clock::now();
	iqs_value = worstCaseIntrospectiveLinearMedian(data_iqs);
	Clock::time_point iqs_t1 = Clock::now();
	microseconds ms_iqs = std::chrono::duration_cast<microseconds>(iqs_t1 - iqs_t0);

	std::cout << n <<"\t"<< "100" <<"\t"<< count_less_or_equal(data_iqs, wst_value) <<"\t"<< count_less_or_equal(data_iqs, iws_value) <<"\t"<< count_less_or_equal(data_iqs, iqs_value) <<"\n";//<<"\t"<< "100" << wst_precision <<"\t"<< wst_precision <<"\n";
	
	
}

int main (int argc, char const *argv[]){
	size_t max_power = (size_t)std::stol(argv[2]);
	size_t min_power = (size_t)std::stol(argv[1]);
	std::string extension = argv[3];
	cout << "# EXPERIMENT FOR "<< extension << " data distribution\n";
	cout << "# N\tAVG\tWST\tIWS\n";//AVG%\tWST%\tIWS%\n";
	for(size_t i = min_power; i < max_power; ++i){
		for(size_t j = 1; j < 10; j++){
			long value = j * pow(10,i);
			do_experiment_presicion(value, extension);
		}
	}
	return 0;
}