#ifndef NEURON_CPP
#define NEURON_CPP

#include <stdlib.h>
#include "neuron.h"
#include <cmath>
#include <random>

std::uniform_real_distribution<> Neuron::unif = std::uniform_real_distribution<>(0.0, 1.0);
std::default_random_engine Neuron::re = std::default_random_engine();


Neuron::Neuron(size_t feature_vector_size){
    this->__feature_vector_size = feature_vector_size;
    this->__feature_vector = (double * ) malloc( sizeof(double) * this->__feature_vector_size );
    
    double a_random_double = unif(re);

    for(size_t i=0; i<this->__feature_vector_size; i++){
        this->__feature_vector[i] = unif(re);
    }
}


double Neuron::get_distance(double *source_feature_vector){
    #ifdef USE_EUCLIDEAN_DISTANCE
        double cummulated = 0.0;
        for(size_t i=0; i<this->__feature_vector_size; i++){
            cummulated += pow(this->__feature_vector[i] - source_feature_vector[i], 2.0); 
        }
        return sqrt(cummulated);
    #else
        return 0;
    #endif // USE_EUCLIDEAN_DISTANCE
}

std::string Neuron::to_string(){
    std::stringstream ss;
    ss << "FACTOR_SIZE=" << this->__feature_vector_size;
    ss << " FEATURE_VECTOR={";
    for(size_t i=0; i<this->__feature_vector_size; i++){
        ss << this->__feature_vector[i];
        if(i<this->__feature_vector_size - 1){
            ss << ", ";
        }
    }
    ss << "}";
    return ss.str();
}

double Neuron::get_feature(size_t index){
    return this->__feature_vector[index];
}

void Neuron::learn(double *feature, double ratio, double effect){
    for(size_t i=0; i<this->__feature_vector_size; i++){
        this->__feature_vector[i] += ratio * effect * ( feature[i] - this->__feature_vector[i]);  
    }
}

#endif /* NEURON_CPP */
