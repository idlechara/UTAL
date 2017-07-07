//============================================================================
// Name        : Proyecto.cpp
// Author      : Erik Andres Regla Torres
// Version     :
// Copyright   : Copialo y te pego! D:
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <sstream>
#include "paging/HeapPaging.h"
#include "paging/Manager.h"
#include "filer/FileArray.h"
#include "filer/FileReader.h"
#include "filer/FileWritter.h"
#include "stdlib.h"
#include "math.h"

using namespace std;

int divide(string source, int pageSize);
int merge(diskpag::FileArray *pageMgr, diskpag::FileWritter *fileOutput,
		mempag::HeapPaging *heap, int generation, int pages);
void loadHeap(mempag::HeapPaging *heap, diskpag::FileArray *pageMgr);
void deleteGeneration(string source, int generation, int pages);

int main(int args, char **argv) {
	try {
		int ways = 2; //defined at runtime? use 2 to get mergeSort, more to get multiWayMergeSort
		int generation = 0, pages = 0, pagesToDelete, buffer = (int) pow(2, 20);
		int accesos = 0;
		int megaElements = 0;
		if (args >= 2) {
			megaElements = atoi(argv[1]);
			//cout << endl << megaElements << endl << endl;
			if (megaElements < 1)
				throw "Error: numero de elementos a mezclar invalido!";
			buffer *= megaElements;
		}
		if (args == 3) {
			string s = "s";
			if (s.data() < argv[2])
			buffer = megaElements;
		}

		//Primer estado, arreglo separado y ordenado
		string filename = "data.bin";	//also defined at runtime
		pages = divide(filename, buffer);

		accesos += 1;
		accesos += 2 * pages;

		pagesToDelete = pages;
		//cout << "paginas escritas en total: " << pages;

		//segundo estado: mezclando por generaciones.
//		diskpag::Manager *mgr;

		diskpag::FileArray *pageMgr = new diskpag::FileArray(ways, filename);
		diskpag::FileWritter *fileOutput = new diskpag::FileWritter(filename,
				buffer);
		mempag::HeapPaging *heap = new mempag::HeapPaging(ways);

		while (pages > 1) {

			pages = merge(pageMgr, fileOutput, heap, generation++, pages);
			accesos += 2 * pages;
			deleteGeneration(filename, generation - 1, pagesToDelete);
			pagesToDelete = pages;
//			std::cout << "Manager ready!" << std::endl;
//			mgr = new diskpag::Manager(filename,generation++,pages,ways);
//			std::cout << "Manager allocation ready!" << std::endl;
//			pages = mgr->mergeGeneration();
//			std::cout << "merge ready!" << std::endl;
		}

		stringstream targetPage;
		targetPage.str("");
		targetPage << "mv " << filename << "_" << generation << "_" << 0 << " "
				<< filename << "_sorted";
		system(targetPage.str().data());
		cout << "accesos al disco 2w: " << accesos<< endl;
//
//		/**
//		 * Testing Heap
//		 */
//		mempag::HeapPaging *heap = new mempag::HeapPaging(5);
//
//		//overflow test
//		heap->push(5);
//		heap->push(1);
//		heap->push(8);
//		heap->push(9);
//		heap->push(0);
//
//		//underflow test
//		while(!heap->isEmpty()){
//			cout << heap->extractMin().value;
//		}
		return accesos;

	} catch (char const *neko) {
		cout << neko;
	}
	return 1;
}

void deleteGeneration(string source, int generation, int pages) {
	stringstream targetPage;
	for (int i = 0; i < pages; i++) {
		targetPage.str("");
		targetPage << "rm " << source << "_" << generation << "_" << i;
		system(targetPage.str().data());
	}

	//cout << targetPage.str() << endl;
}

void loadHeap(mempag::HeapPaging *heap, diskpag::FileArray *pageMgr) {
	for (int i = 0; i < pageMgr->size(); i++)
		heap->push(pageMgr->readStruct(i));
}

int merge(diskpag::FileArray *pageMgr, diskpag::FileWritter *fileOutput,
		mempag::HeapPaging *heap, int generation, int pages) {
	//cout << "merging!" << endl;
	int writtedPages = 0;
	int currentPage = 0;

	while (currentPage < pages) {
		//cout << "merging ["<< currentPage <<"]" << endl;
		//loads a x number of pages
		while (currentPage < pages && !pageMgr->full()) {
			pageMgr->add(generation, currentPage++);
		}

		loadHeap(heap, pageMgr);

		t_pageValue temp;
		fileOutput->init(generation + 1, writtedPages);

		//merges the files
		while (!heap->isEmpty()) {
			//reads an writes onto the buffer
			temp = heap->extractMin();
			//cout << "extracting "<< temp.value << " from page " << temp.page<< endl;
			fileOutput->write(temp.value);

			//attempts to reload the page... if has more data.
			temp = pageMgr->readStruct(temp.page);
			if (temp.value >= 0)
				heap->push(temp);
		}
		fileOutput->closeAndFlush();
		pageMgr->reset();
		writtedPages++;
	}
	return writtedPages;

}

int divide(string source, int pageSize) {
	int pages = 0, actualValue;

	ifstream input;
	input.open(source.data(), ios::in | ios::binary);

	stringstream targetPage;
	ofstream output;

	//cout << "entra a divide!!" << endl << flush;
	mempag::HeapPaging *buf = new mempag::HeapPaging(pageSize);

	bool nonEndOfFile = true;

	//cout << "Starting cycle..." << endl;

	while (nonEndOfFile) {
		//cout << "Iteration done!" << endl;

		if (!input.is_open()) {
			//cout << "File is closed!" << endl;
			//cout << "error! " << endl;
			nonEndOfFile = false;
		} else {
			//cout << "overflow?.." << endl;
			if (buf->isFull()) {
				//cout << "Buffer overflow!... dumping." << endl;
				targetPage.str("");
				targetPage << source << "_0_" << pages++;
				//cout << targetPage.str() << endl;
				output.open(targetPage.str().data(), ios::out | ios::binary);
				buf->dump(&output);
				buf->clear();
				output.close();
			}

			//cout << "eof?.." << endl;
			if (input.eof()) {
				//cout << "End of file reached... dumping." << endl;
				//cout << "endOfFile! " << endl;
				nonEndOfFile = false;
				if (pages >= 0) {
					targetPage.str("");
					targetPage << source << "_0_" << pages++;
					//cout << targetPage.str() << endl;
					output.open(targetPage.str().data(),
							ios::out | ios::binary);
					buf->dump(&output);
					buf->clear();
					output.close();
				}
			} else {
				//cout << "reading: " << actualValue << " - page: " << endl;
				input.read((char *) &actualValue, sizeof(actualValue));
				buf->push(actualValue);
				//cout << "waiting next iteration: " << endl;
			}
		}

	}
	delete buf;
	//cout << "sale de divide D:" << endl << flush;
	return pages;
}

