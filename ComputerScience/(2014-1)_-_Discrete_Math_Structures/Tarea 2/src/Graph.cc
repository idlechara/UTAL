// The MIT License (MIT)
//
// Blumenkranz - A graph testing application for a Discrete Computational
// Structures course - UTalca
// 
// Copyright (c) [2014] [Erik Andrés Regla Torres]
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
#ifndef GRAPH_CC_
#define GRAPH_CC_

#include <vector>
#include <iostream>
#include <sstream>
#include <unordered_map>
#include <tuple>

namespace Structures {
class Graph{
public:
        Graph(){
        };

        Graph(const Graph& target) : directed(target.directed),
                                    vertex_count(target.vertex_count),
                                    edges_count(target.edges_count),
                                    vertex_list(target.vertex_list){};

        Graph(int vertex_count, bool directed):
            vertex_count(vertex_count), directed(directed){
            this->vertex_list.reserve(vertex_count);
            for (int index = 0; index < vertex_count; ++index){
                this->vertex_list.push_back(std::unordered_map<int, int>());
            }
        };
        ~Graph(){};

        int V() const {
            return vertex_count;
        };

        int E() const {
            return (directed)? edges_count : (int) (edges_count / 2);
        };

        bool is_directed() const{
            return this->directed;
        };

        int insert( int source,  int target){
            edges_count++;
            this->vertex_list.at(source)[target] += 1;
        };

        int remove( int source,  int target){
            int destination_count = this->vertex_list.at(source)[target];
            int origin_count = this->vertex_list.at(target)[source];

            if(this->vertex_list.at(source)[target] > 1)                        //deleting src -> dest
                this->vertex_list.at(source)[target] -= 1;
            else
                this->vertex_list.at(source).erase(target);

            if(!this->directed && this->vertex_list.at(target)[source] > 1)     //deleting dest -> src
                this->vertex_list.at(target)[source] -= 1;
            else if(!this->directed)
                this->vertex_list.at(target).erase(source);
            edges_count -= (this->directed)? 2 : 1;
        };

        bool edge( int source,  int target) const {
            try {this->vertex_list[source].at(target);}
            catch (const std::out_of_range& oor) {return false;}
            return true;
        };

        std::vector<int> neighbours(int target){
            std::vector<int> data;
            for (std::pair<const int, int> &value: this->vertex_list[target])   //get each pair of data
                if(value.second > 0) data.push_back(value.first);               //then translates it onto a vector
            return data;
        }

        int get_random_neightbour(int target){
            for (std::pair<const int, int> &value: this->vertex_list[target])   //gets the first value found the map
                if(value.second > 0) return value.first;
            return -1;                                                          //if it's empty, then returns -1
        }


    private:
        bool directed = false;
        int vertex_count = 0 , edges_count = 0;
        std::vector<std::unordered_map<int,int>> vertex_list;

        friend std::ostream& operator<<(std::ostream &strm, Graph &target){
            for ( int idx = 0; idx < target.vertex_count; ++idx){
                strm << idx << ": ";
                for (std::pair<const int, int> &data: target.vertex_list[idx]){
                    for (int times = 0; times < data.second; ++times){
                        strm  << data.first << " ";
                    }
                }
                strm << std::endl;
            }
            return strm;
        };
};
} // Structures

#endif
