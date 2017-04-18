//============================================================================
// Name        : t1.cpp
// Author      : jvarred
// Version     : 1.0
// Copyright   : Erik Andrés Regla Torres ©2014
// Description : Assgnment 1 - Algorithms and Data Structures course 2014-1
//============================================================================

#include <iostream>
#include <cstring>
#include <malloc.h>
using namespace std;

//#define DEBUG                         //uncomment to enter debug mode, or compile with -DDEBUG
#define SHOW(a) std::cout << #a << ": " << (a) << std::endl
#define RAPAS_NUMBER 1000000000L        //there is nothing to say about this number, 1e9 will also do
#define MAX_TRIANGULAR_NUMBER 44730     //precalculated

//bitmasks for unsigned char
#define BIT_0        0b00000001
#define BIT_1        0b00000010
#define BIT_2        0b00000100
#define BIT_3        0b00001000
#define BIT_4        0b00010000
#define BIT_5        0b00100000
#define BIT_6        0b01000000
#define BIT_7        0b10000000
#define BIT_CLEAR    0b00000000

//global variables
unsigned char bitmask[8] = {BIT_0, BIT_1, BIT_2, BIT_3, BIT_4, BIT_5, BIT_6, BIT_7};
int error = 0;


/********************************
BITWISE MASKING/SHIFTING FUNCTIONS
********************************/
inline void bit_on(unsigned char *&target, long index){
    target[index>>3] |= bitmask[index%8];
}

inline void bit_off(unsigned char *&target, long index){
    target[index>>3] &= !bitmask[index%8];
}

inline int check_bit(unsigned char *&target, long index){
    return ((target[index>>3]&bitmask[index%8]) == bitmask[index%8]);
}

void init_bit_array(unsigned char *&target, long size){
    long bit_array_size = (size/8)+1;
    target = new unsigned char[bit_array_size];
    for(long i=0; i<bit_array_size; i++){
        target[i] = BIT_CLEAR;
    }
    return;
}    //end init_bit_array

void copy_bit_array(unsigned char *&target, unsigned char *&source, long size){
    long bit_array_size = (size/8)+1;
    for(long i=0; i<bit_array_size; i++){
        target[i] = source[i];
    }
    return;
}    //end init_bit_array

void copy_bit_array_with_offset(unsigned char *&target, unsigned char *&source, long size, long offset){
    for(long i=0; i+offset < size; i++){
        if(check_bit(source, i)) bit_on(target,i+offset);
    }
    return;
}    //end init_bit_array



/********************************
ARRAY & INITIALIZATOR FUNCTIONS
********************************/
//initialies an array of longs
void init_array(long *&target, long size){
    target = new long[size];        //init pointer size;
    // memset (target,0,size);        //sets all data as zeroes - UNSAFE
    for(long i=0; i<size; i++){        //sets all data as zeroes
        target[i] = 0L;
    }
    return;
}    //end init_array

//populates an array of longs with triangular numbers
void populate_trianglular_numbers(long *&target, long size){
    long current = 0L;                        //current value of the sucession (triangular number)
    long count = 0L;
    target[0] = (char)1;                    //initializes the value of the first element
    while( count < size){                    //cycles until current exceeds the max size
        target[count] = current;            //sets the value as 1, that means, the number
        count++;
        current += count;
    }
    #ifdef DEBUG
        //cout << "#triangular_numbers: " << count-1 << endl;
    #endif
    return;
}    //end populate_trianglular_numbers


/********************************
INDEXING FUNCTIONS
********************************/
//optimized for incremental search
void search_index_below_or_equal(long *&target, long size, long value, long &index){
    if(value < 0){index=0;return;}
    else if((target[index] <= value && target[index+1] > value)){ return;}

    else if(target[index] > value){
        while((target[index] > value)) index--;
    }
    else{
        while(!(target[index+1] > value)){
            index++;
        }
    }
    return;
}


/********************************
COMPARATORS
********************************/
inline long sum_ge(long *&target, long a, long b, long c, long value){
    return (target[a] + target[b] + target[c]) >= value;
}
inline long sum_e(long *&target, long a, long b, long c, long value){
    return (target[a] + target[b] + target[c]) >= value;
}
inline long sum_le(long *&target, long a, long b, long c, long value){
    return (target[a] + target[b] + target[c]) >= value;
}

void solve_descomposition(long *&target, long size, long limit, long value, long &k, long &j, long &i){
    j=i=0;
    for(k=size; k>limit; k--){
        search_index_below_or_equal(target, size, value - target[k] ,j);
        for(; (j>=i); j--){
            search_index_below_or_equal(target, size, value - target[k] - target[j] ,i);
            if (sum_e(target,i,j,k,value)) return;
        }
    }
    #ifdef DEBUG
        SHOW(k);
        SHOW(j);
        SHOW(i);
    #endif
    error = 1;
    return;    
}

int main() {
    #ifdef DEBUG
        cout << "****************************************************" << endl;
        cout << "*                                                  *" << endl;
        cout << "*    Algorithms and Data Structures Assignment 1   *" << endl;
        cout << "*        Silent Shizuru 1.0 - DEBUG MODE           *" << endl;
        cout << "*                                                  *" << endl;
        cout << "****************************************************" << endl;
    #endif

    //variables
    long *triangular_numbers;
    long index = 0L, a=0L,b=0L,c=0L;
    long to_solve = RAPAS_NUMBER;

    //initializes triangular number's lookup table
    init_array(triangular_numbers, MAX_TRIANGULAR_NUMBER);
    populate_trianglular_numbers(triangular_numbers, MAX_TRIANGULAR_NUMBER);

    #ifdef DEBUG
        cout << "STAGE 0: Done." << triangular_numbers[4] << endl;
    #endif

    //waits for...
    while(to_solve >= 0){
        cin >> to_solve;
        error = 0;
        if(to_solve < 0) break;  
        else{
            //begins a descomposition
            search_index_below_or_equal(triangular_numbers, MAX_TRIANGULAR_NUMBER, to_solve,index);
            solve_descomposition(triangular_numbers, index, ((index)/3), to_solve,a,b,c);    

            #ifdef DEBUG
                cout << "Below index for "<< to_solve << ": " << index << " value: " << triangular_numbers[index] << endl;
                cout << "Descomposition for "<< to_solve << ": " <<
                 triangular_numbers[a] << " + "  <<
                 triangular_numbers[b] << " + "  <<
                 triangular_numbers[c] << endl<< "----------------------"<< endl<< endl ;
            #else
                 cout << to_solve << ": " << triangular_numbers[a];
                 if(triangular_numbers[b] > 0) cout << " " << triangular_numbers[b];
                 if(triangular_numbers[c] > 0) cout << " " << triangular_numbers[c];
                 cout << endl;
            #endif    
        }
    }
    free(triangular_numbers);
    return 0;
}   //end main
