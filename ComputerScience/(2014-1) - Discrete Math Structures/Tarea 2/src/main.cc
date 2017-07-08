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

#include <stdexcept>
#include <iostream>
#include <vector>
#include <sstream>
#include <unordered_map>
#include "HybridBFS.cc"
#include "Hierhozer.cc"
#include "Graph.cc"
#include "IO.cc"

int main(int argc, char const *argv[])
{
    std::vector<Structures::Graph> grafo = Structures::IO().readGraph();
    // Structures::IO().printGraphs(grafo);

    int c = 0;
    for (Structures::Graph &target: grafo){

        #ifdef BIPARTITE
            Algorithms::HybridBFS().bipartite_checking(target);
        #endif

        #ifdef EULER
            Algorithms::Hierhozer().eulerCycle(target);
        #endif

        #ifdef COMPONENTS
            Algorithms::HybridBFS().connectivity_checking(target);
        #endif

        c++;
    }
    return 0;
}