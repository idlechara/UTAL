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
#ifndef HIERHOZER_CC_
#define HIERHOZER_CC_

#include "IO.cc"
#include "Graph.cc"
#include <vector>
#include <queue>
#include <stack>
#include <sstream>
#include <string>
#include <unordered_set>
#include <unordered_map>

namespace Algorithms{
    class Hierhozer{
    public:
        static int eulerCycle(Structures::Graph &target){
            if(!hasEulerCycle(target)){
                std::cout << "NO" << std::endl;
                return -1;
            }

            std::cout << "SI" << std::endl;

            std::stack<int> node_stack;                                                    //Initialize data structures
            Structures::Graph temp_graph(target);                                          //and required initialization values
            int src_node = 0, dest_node = 0;

            node_stack.push(temp_graph.get_random_neightbour(src_node));                   //pushes the first node avaliable on the graph

            std::cout << "Ciclo = ";
            while(node_stack.size() > 0){                                                  //while the stack is non-empty
                dest_node = temp_graph.get_random_neightbour(src_node = node_stack.top()); //peeks at the first element
                if(dest_node == -1){                                                       //if we are on a dead end
                    std::cout << src_node << " ";                                          //prints node as part of the circuit
                    node_stack.pop();                                                      //then pops the node out of the stack
                }
                else{                                                                      //otherwise
                    node_stack.push(dest_node);                                            //continue pushing elements on the stack
                    temp_graph.remove(src_node, dest_node);                                //and erase (mark) edge
                }
            }
            std::cout << std::endl;
            return 0;
        }

    private:
        static bool hasEulerCycle(Structures::Graph &target){                             //if all the nodes have an odd number od egdes
            for(int i=0; i< target.V(); i++){                                             //then /target/ is an euler graph.
                if(target.neighbours(i).size() % 2 == 1)
                    return false;
            }
            return true;
        }
    };
}

#endif
