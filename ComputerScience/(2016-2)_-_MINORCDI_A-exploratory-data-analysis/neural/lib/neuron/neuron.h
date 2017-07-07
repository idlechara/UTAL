#ifndef NEURON_H
#define NEURON_H
#define USE_EUCLIDEAN_DISTANCE

#include <string>
#include <sstream>
#include <functional>
#include <stdlib.h>
#include "neuron.cpp"
#include <random>

class Neuron{
    private:
        // double *__feature_vector;
        size_t __feature_vector_size;
        static std::uniform_real_distribution<> unif;
        static std::default_random_engine re;
    public:
        double *__feature_vector;
        double u_distance = 0;
        Neuron(size_t feature_vector_size);
        double get_distance(double *source_feature_vector);
        std::string to_string();
        double get_feature(size_t index);
        void learn(double *feature, double ratio, double effect);
};

#endif /* NEURON_H */
