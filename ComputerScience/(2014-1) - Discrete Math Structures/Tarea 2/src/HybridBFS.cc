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
#ifndef HYBRIDBFS_CC_
#define HYBRIDBFS_CC_

#include "IO.cc"
#include "Graph.cc"
#include <vector>
#include <queue>
#include <stack>
#include <sstream>
#include <string>
#include <unordered_set>
#include <unordered_map>

#define COLOUR(X) getIntAt(colour_map, X)
#define VISITED(X) getBoolAt(visited, X)

namespace Algorithms{
class HybridBFS{

private:
    static int getIntAt(std::unordered_map<int,int> &map, int idx){
            int value=0;
            try {value = map.at(idx);}
            catch (const std::out_of_range& oor) {}
            return value;
    }

    static bool getBoolAt(std::unordered_map<int,bool> &map, int idx){
            int value=0;
            try {value = map.at(idx);}
            catch (const std::out_of_range& oor) {}
            return value;
    }

public:

    static int connectivity_checking(Structures::Graph &target){
        int c = 0;
        std::unordered_map<int, bool> visited;
        std::queue<int> bfs_queue;
        std::unordered_set<int> remaining;

        for (int idx = 0; idx < target.V(); ++idx)                              //Insert nodes on pending list
            remaining.insert(idx);

        visited[0] = true;                                                      //mark the first node as visited
        bfs_queue.push(0);                                                      //insert the first node.
        remaining.erase(0);                                                     //removes the first node from the pending list

        //MAIN LOOP BEGIN
        while(bfs_queue.size() > 0){                                            //If the queue isn't empty...
            int current_node = bfs_queue.front(); bfs_queue.pop();              //pops an element
            int current_visited = VISITED(current_node);                        //gets the current colour of a node. 0=no, 1=red, 2=black
            for (const int &data: target.neighbours(current_node)){             //for each neighbour of the current node
                if (!VISITED(data)){                                            //if not added nor marked
                    visited[data] = true;                                       //insert node with an analogue mark
                    bfs_queue.push(data);
                }
            }
            remaining.erase(current_node);                                      //removes current node from remaining

            if(bfs_queue.size() == 0){                                          //if scanning is finished
                if(remaining.size() > 0){                                       //and there is more elements
                    for (const int &random_element: remaining){                 //inject another element to the queue
                        bfs_queue.push(random_element);
                        visited[random_element] = true;
                        remaining.erase(random_element);
                        break;
                    }
                }
                c++;
            }
        }
        std::cout << "El numero de componentes es " << c << std::endl;
        return true;
    }

    static bool bipartite_checking(Structures::Graph &target){
        std::unordered_map<int, int> colour_map;
        std::queue<int> bfs_queue;
        std::unordered_set<int> remaining;

        for (int idx = 0; idx < target.V(); ++idx)                              //Insert nodes on pending list
            remaining.insert(idx);

        colour_map[0] = 1;                                                      //mark the first node as red
        bfs_queue.push(0);                                                      //insert the first node.
        remaining.erase(0);                                                     //removes the first node from the pending list

        //MAIN LOOP BEGIN
        while(bfs_queue.size() > 0){                                            //If the queue isn't empty...
            int current_node = bfs_queue.front(); bfs_queue.pop();              //pops an element
            int current_colour = COLOUR(current_node);                          //gets the current colour of a node. 0=no, 1=red, 2=black
            for (const int &data: target.neighbours(current_node)){             //for each neighbour of the current node
                int neighbour_color = COLOUR(data);
                if(current_colour == neighbour_color){                          //if the neighbour is the same...
                    std::cout << "NO" << std::endl;
                    return false;
                }
                else if (0==COLOUR(data)){                                      //if not added nor marked
                    colour_map[data] = (current_colour==1)? 2 : 1;              //insert node with an analogue mark
                    bfs_queue.push(data);
                }
            }
            remaining.erase(current_node);                                      //removes current node from remaining
        }
        //MAIN LOOP END

        if(remaining.size() > 0){                                               //checks if there are a remaining item
            std::cout << "NO" << std::endl;
            return false;
        }

        std::cout << "SI" << std::endl << "partU = { ";                         //prints data
        for (std::pair<const int,int> &data: colour_map){
            if(data.second == 1)
                std::cout << data.first << " ";
        }
        std::cout <<"}" << std::endl;

        std::cout << "partV = { ";
        for (std::pair<const int,int> &data: colour_map){
            if(data.second == 2)
                std::cout << data.first << " ";
        }
        std::cout <<"}" << std::endl;

        return true;
    };
};

} //Algorithms

#endif
