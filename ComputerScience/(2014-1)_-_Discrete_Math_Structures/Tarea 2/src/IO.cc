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
#ifndef IO_CC_
#define IO_CC_


#include <stdexcept>
#include <iostream>
#include <vector>
#include <sstream>
#include <unordered_map>
#include "Graph.cc"

namespace Structures {
class IO
{
public:
    static std::vector<Structures::Graph> readGraph(){
        std::string line, number;

        int g = 0, n = 0;
        getline(std::cin, line);
        g = std::stoi(line);
        std::vector<Structures::Graph> grafos;
        for (int i = 0; i < g; ++i)
        {
            getline(std::cin, line);
            n = std::stoi(line);
            Structures::Graph graph(n, false);
            for (int j = 0; j < n; ++j){                                        //for each node do...
                std::vector<int> edges;                                         //init edge array
                int connected_to = 0;                                           //read each edge and add it
                getline(std::cin, line);
                std::stringstream linestream(line);
                linestream >> number;
                while(linestream >> number){
                    graph.insert(j, std::stoi(number));
                }
            }
            grafos.push_back(graph);
        }
        return grafos;
    }

    static void printGraphs(std::vector<Structures::Graph> &target){
        for (Structures::Graph &g: target){
            std::cout << g << std::endl;
        };
    }
};
} // Structures

#endif
