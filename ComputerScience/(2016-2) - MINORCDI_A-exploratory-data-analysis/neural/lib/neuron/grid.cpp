#ifndef GRID_CPP
#define GRID_CPP

#include <stdlib.h>
#include <iostream>
#include "grid.h"
#include <float.h>
#include <cmath>
#include <unordered_set>
#include <unordered_map>


Grid::Grid(size_t x_dim, size_t y_dim, size_t factor_dim,
                size_t max_radius, size_t min_radius,
                size_t iterations, double initial_learning_rate, double final_learning_rate, size_t pixel_size){
    this->__pixel_size = pixel_size;
    this->__x_dim = x_dim;
    
    this->__factor_dim = factor_dim;
    this->__y_dim = y_dim;

    this->__viewport = new Viewport(this->__pixel_size*this->__x_dim, this->__pixel_size*this->__y_dim);
    this->__viewport->clear();

    this->__grid = (Neuron **) malloc( sizeof(Neuron*) * this->__x_dim);
    for(size_t i=0; i<this->__x_dim; i++){
        this->__grid[i] = (Neuron *) malloc( sizeof(Neuron) * this->__y_dim);
        for(size_t j=0; j<this->__y_dim; j++){
            this->__grid[i][j] = Neuron(this->__factor_dim);
            this->__viewport->draw_dot( (int) i,
                                        (int) j,
                                        (int) this->__pixel_size, 
                                        (char)(unsigned char)(255*this->__grid[i][j].get_feature(0)),
                                        (char)(unsigned char)(255*this->__grid[i][j].get_feature(1)),
                                        (char)(unsigned char)(255*this->__grid[i][j].get_feature(2)));
        }
    }
    this->__epoch = 0;
    this->__viewport->update();
    this->__max_radius = max_radius;
    this->__min_radius = min_radius;
    this->__iterations = iterations;
    this->__lambda = this->__iterations/log(this->__max_radius);
    this->__initial_learning_rate = initial_learning_rate;
    this->__final_learning_rate = final_learning_rate;
    this->__current_learning_rate = this->__initial_learning_rate;
}

void Grid::present_bmu_sample(double *factor, size_t factor_dim, size_t index){
    size_t BMU_x=0, BMU_y=0;
    double current_distance = DBL_MAX;
    //GET BMU
    for(size_t i=0; i<this->__x_dim; i++){
        for(size_t j=0; j<this->__y_dim; j++){
            double dist = this->__grid[i][j].get_distance(factor);
            //std::cout << "dist=" << dist << std::endl;
            if(dist <= current_distance){
                BMU_x = i;
                BMU_y = j;
                current_distance = dist;
            }
        }
    }
    if(this->__bmu_map.find(this->__x_dim * BMU_x + BMU_y) == this->__bmu_map.end()){
        this->__bmu_map[this->__x_dim*BMU_x + BMU_y] = std::vector<size_t>();    
    }
    this->__bmu_map[this->__x_dim*BMU_x + BMU_y].push_back(index);
}

void Grid::present_sample(double *factor, size_t factor_dim){
    size_t BMU_x=0, BMU_y=0;
    double current_distance = DBL_MAX;

    //those two nested for are part of the evil
    for(size_t i=0; i<this->__x_dim; i++){
        for(size_t j=0; j<this->__y_dim; j++){
            double dist = this->__grid[i][j].get_distance(factor);
            //std::cout << "dist=" << dist << std::endl;
            if(dist <= current_distance){
                BMU_x = i;
                BMU_y = j;
                current_distance = dist;
            }
        }
        
        //getchar();
    }
    this->__bmu_set.insert(this->__x_dim*BMU_x + BMU_y);
    // std::cout << "__max_radius=" << this->__max_radius << "\n";
    // std::cout << "preradius:" << this->__max_radius * exp(-(double)this->__epoch / this->__lambda) << "\n";
    //COMPUTE EFECTIVE RADIUS
    double radius = std::max(this->__max_radius * exp(-(double)this->__epoch / this->__lambda), (double) this->__min_radius);

    // std::cout << "RADIUS=" << radius << "\n"; 
    
    //optimize this kNN query later
    // std::cout << "BMU X=" << BMU_x << " Y=" << BMU_y << "\n";
    // this->repaint();


    #ifdef MANHATTAN_DISTANCE_BMU
    //Sets limits
    for(size_t i=BMU_x-radius; i<BMU_x+radius; i++){
        for(size_t j=BMU_y-radius=0; BMU_y-radius; j++){
            size_t dist_BMU_x = BMU_x-i, dist_BMU_y = BMU_y-j;
            double distance_squared = (dist_BMU_x*dist_BMU_x) + (dist_BMU_y*dist_BMU_y);
            if( distance_squared <= radius * radius ){
                double AoE = exp(-(distance_squared) / (2*radius * radius));
                this->__grid[i][j].learn(factor, AoE, this->__current_learning_rate);
            }
        }
    }
    #else
        for(size_t i=0; i<this->__x_dim; i++){
            for(size_t j=0; j<this->__y_dim; j++){

                size_t dist_BMU_x = BMU_x-i, dist_BMU_y = BMU_y-j;

                double distance_squared = (dist_BMU_x*dist_BMU_x) + (dist_BMU_y*dist_BMU_y);

                if( distance_squared <= radius * radius ){
                    double AoE = exp(-(distance_squared) / (2*radius * radius));
                    //double AoE =  ((radius * radius) / (distance_squared))*0.05;
                    // std::cout << "AOE:" << AoE << std::endl;
                    // std::cout << "learning_rate:" << this->__current_learning_rate << std::endl;
                    // std::cout << "init_learning_rate:" << this->__initial_learning_rate << std::endl;
                    this->__grid[i][j].learn(factor, AoE, this->__current_learning_rate);
                    // std::cout << "exponential_decay=" << exp(-(double)this->__epoch / this->__lambda) << std::endl;
                    //calculate BMU effect over this neuron
                    // this->__viewport->draw_dot( (int) i,
                    //                            (int) j,
                    //                            (int) this->__pixel_size, 
                    //                            (char)(unsigned char)(255*this->__grid[i][j].get_feature(0)),
                    //                             (char)(unsigned char)(255*this->__grid[i][j].get_feature(1)),
                    //                             (char)(unsigned char)(255*this->__grid[i][j].get_feature(2)));
                                            //    (char)(unsigned char)(255),
                                            //    (char)(unsigned char)(255),
                                            //    (char)(unsigned char)(255));
                    //std::cout << "X=" << i << " Y=" << j << "\n";
                    // getchar();
                }
            }
        }
    #endif // MANHATTAN_DISTANCE_BMU

    
    // this->__viewport->update();
    // this->__viewport->update();
    // getchar();
}

void inline Grid::advance_epoch(){
    // this->__initial_learning_rate = (double)(this->__iterations - this->__epoch) / this->__iterations;
    this->__current_learning_rate = std::max(this->__initial_learning_rate * exp(-(double)this->__epoch / this->__lambda), (double) this->__final_learning_rate);
    // std::cout << this->__initial_learning_rate << std::endl;
    this->__epoch++;
}

void inline Grid::clear_bmu(){
    this->__bmu_set.clear();
}

void inline Grid::update(){
    this->__viewport->update();
}
bool get_heat_map_color(float value, float *red, float *green, float *blue)
{
    const int NUM_COLORS = 4;
    static float color[NUM_COLORS][3] = { {0,0,1}, {0,1,0}, {1,1,0}, {1,0,0} };
        // A static array of 4 colors:  (blue,   green,  yellow,  red) using {r,g,b} for each.

    int idx1;        // |-- Our desired color will be between these two indexes in "color".
    int idx2;        // |
    float fract_between = 0;  // Fraction between "idx1" and "idx2" where our value is.

    if(value <= 0)      {  idx1 = idx2 = 0;            }    // accounts for an input <=0
    else if(value >= 1)  {  idx1 = idx2 = NUM_COLORS-1; }    // accounts for an input >=0
    else
    {
        value = value * (NUM_COLORS-1);        // Will multiply value by 3.
        idx1  = floor(value);                  // Our desired color will be after this index.
        idx2  = idx1+1;                        // ... and before this index (inclusive).
        fract_between = value - float(idx1);    // Distance between the two indexes (0-1).
    }

    *red   = (color[idx2][0] - color[idx1][0])*fract_between + color[idx1][0];
    *green = (color[idx2][1] - color[idx1][1])*fract_between + color[idx1][1];
    *blue  = (color[idx2][2] - color[idx1][2])*fract_between + color[idx1][2];
}

void Grid::repaint_heatmap(int feature){
    #ifdef LAST_COLUMN_AS_CLASS
    if(feature == this->__factor_dim-1){        
        float r,g,b;
        for(size_t i=0; i<this->__x_dim; i++){
            for(size_t j=0; j<this->__y_dim; j++){
                get_heat_map_color(this->__grid[i][j].get_feature(feature), &r,&g,&b);
                this->__viewport->draw_dot( (int) i,
                                            (int) j,
                                            (int) this->__pixel_size, (char)(r*255),(char)(g*255),(char)(b*255));
                // std::cout << "RGB:{"<< 
                // (int)(unsigned char)(255*this->__grid[i][j].get_feature(0)) << ", "<<
                // (int)(unsigned char)(255*this->__grid[i][j].get_feature(1)) << ", "<<
                // (int)(unsigned char)(255*this->__grid[i][j].get_feature(2)) << "}\n";

                if(this->__bmu_set.count(this->__x_dim*i + j)>0){
                    this->__viewport->draw_cage( (int) i,
                                                 (int) j,
                                                 (int) this->__pixel_size, 0,0,0);
                }
            }
        }
        this->__viewport->update();
        return;
    }
    #endif
    float r,g,b;
    for(size_t i=0; i<this->__x_dim; i++){
        for(size_t j=0; j<this->__y_dim; j++){
            get_heat_map_color(this->__grid[i][j].get_feature(feature), &r,&g,&b);
            this->__viewport->draw_dot( (int) i,
                                        (int) j,
                                        (int) this->__pixel_size, (char)(r*255),(char)(g*255),(char)(b*255));
            
            if(this->__bmu_set.count(this->__x_dim*i + j)>0){
                this->__viewport->draw_cage( (int) i,
                                             (int) j,
                                             (int) this->__pixel_size,0,0,0);
            }
            // std::cout << "RGB:{"<< 
            // (int)(unsigned char)(255*this->__grid[i][j].get_feature(0)) << ", "<<
            // (int)(unsigned char)(255*this->__grid[i][j].get_feature(1)) << ", "<<
            // (int)(unsigned char)(255*this->__grid[i][j].get_feature(2)) << "}\n";
        }
    }
    this->__viewport->update();
}

double normalize_2(double target, double start, double end){
    target -= start;    // offsets target
    end -= start;       // offsets ending
    //at this point, we assume starting at 0.0
    return target/end;
}

void Grid::repaint_umatrix(bool white){
    double max_u_d = DBL_MIN;
    double min_u_d = DBL_MAX;
    for(size_t i=0; i<this->__x_dim; i++){
        for(size_t j=0; j<this->__y_dim; j++){
            this->__grid[i][j].u_distance = 0;

            //get neigh dat
            size_t x_cor = i + 1;
            size_t y_cor = j;
            if(x_cor < this->__x_dim && x_cor >= 0 && y_cor < this->__y_dim && y_cor >= 0){
                this->__grid[i][j].u_distance += this->__grid[i][j].get_distance(this->__grid[x_cor][y_cor].__feature_vector);
            }
            x_cor = i; y_cor = j + 1;
            if(x_cor < this->__x_dim && x_cor >= 0 && y_cor < this->__y_dim && y_cor >= 0){
                this->__grid[i][j].u_distance += this->__grid[i][j].get_distance(this->__grid[x_cor][y_cor].__feature_vector);
            }
            x_cor = i - 1; y_cor = j;
            if(x_cor < this->__x_dim && x_cor >= 0 && y_cor < this->__y_dim && y_cor >= 0){
                this->__grid[i][j].u_distance += this->__grid[i][j].get_distance(this->__grid[x_cor][y_cor].__feature_vector);
            }
            x_cor = i ; y_cor = j - 1;
            if(x_cor < this->__x_dim && x_cor >= 0 && y_cor < this->__y_dim && y_cor >= 0){
                this->__grid[i][j].u_distance += this->__grid[i][j].get_distance(this->__grid[x_cor][y_cor].__feature_vector);
            }

            if(max_u_d < this->__grid[i][j].u_distance){
                max_u_d = this->__grid[i][j].u_distance;
            }
            if(min_u_d > this->__grid[i][j].u_distance){
                min_u_d = this->__grid[i][j].u_distance;
            }
        }
    }

    for(size_t i=0; i<this->__x_dim; i++){
        for(size_t j=0; j<this->__y_dim; j++){
            this->__grid[i][j].u_distance = normalize_2(this->__grid[i][j].u_distance,  min_u_d,max_u_d);
        }
    }
    float r,g,b;
    for(size_t i=0; i<this->__x_dim; i++){
        for(size_t j=0; j<this->__y_dim; j++){
            r = g = b = this->__grid[i][j].u_distance;
            this->__viewport->draw_dot( (int) i,
                                        (int) j,
                                        (int) this->__pixel_size, (char)(r*255),(char)(g*255),(char)(b*255));
            
            if(this->__bmu_set.count(this->__x_dim*i + j)>0){
                this->__viewport->draw_cage( (int) i,
                                             (int) j,
                                             (int) this->__pixel_size,0,255,0);
            }
            // std::cout << "RGB:{"<< 
            // (int)(unsigned char)(255*this->__grid[i][j].get_feature(0)) << ", "<<
            // (int)(unsigned char)(255*this->__grid[i][j].get_feature(1)) << ", "<<
            // (int)(unsigned char)(255*this->__grid[i][j].get_feature(2)) << "}\n";
        }
    }
    this->__viewport->update();
}

void Grid::repaint(){
    for(size_t i=0; i<this->__x_dim; i++){
        for(size_t j=0; j<this->__y_dim; j++){
            this->__viewport->draw_dot( (int) i,
                                        (int) j,
                                        (int) this->__pixel_size, 
                                        (char)(unsigned char)(255*this->__grid[i][j].get_feature(0)),
                                        (char)(unsigned char)(255*this->__grid[i][j].get_feature(1)),
                                        (char)(unsigned char)(255*this->__grid[i][j].get_feature(2)));

            if(this->__bmu_set.count(this->__x_dim*i + j)>0){
                this->__viewport->draw_cage( (int) i,
                                             (int) j,
                                             (int) this->__pixel_size);
            }
            // std::cout << "RGB:{"<< 
            // (int)(unsigned char)(255*this->__grid[i][j].get_feature(0)) << ", "<<
            // (int)(unsigned char)(255*this->__grid[i][j].get_feature(1)) << ", "<<
            // (int)(unsigned char)(255*this->__grid[i][j].get_feature(2)) << "}\n";
        }
    }
    this->__viewport->update();
}
#endif /* GRID_CPP */
