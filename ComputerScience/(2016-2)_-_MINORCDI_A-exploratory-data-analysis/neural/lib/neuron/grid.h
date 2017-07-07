#ifndef GRID_H
#define GRID_H

#include <unordered_set>
#include <unordered_map>
#include "neuron.cpp"
#include "grid.cpp"
#include "../viewport/viewport.cpp"

class Grid{
    private:
        std::unordered_set< size_t > __bmu_set;
        
        Neuron **__grid;
        size_t __x_dim, __y_dim, __factor_dim;
        int __pixel_size = 10;
        size_t __epoch, __max_radius, __min_radius, __iterations;
        double __lambda;
        double __initial_learning_rate, __final_learning_rate, __current_learning_rate;

    public:
        Viewport *__viewport, *result_viewport;
        Grid(   size_t x_dim, size_t y_dim, size_t factor_dim,
                size_t max_radius, size_t min_radius,
                size_t iterations, double initial_learning_rate, double final_learning_rate,size_t pixel_size);
        std::unordered_map< size_t, std::vector<size_t> > __bmu_map;
        void present_sample(double *factor, size_t factor_dim);
        void advance_epoch();
        void clear_bmu();
        void update();
        void repaint_heatmap(int feature);
        void repaint_umatrix(bool white = false);
        void present_bmu_sample(double *factor, size_t factor_dim, size_t index);
        void repaint();

        //void Initialize(size_t x_dim, size_t y_dim, size_t factor_dim);
};

#endif /* GRID_H */
