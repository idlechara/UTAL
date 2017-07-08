#include <iostream>
#include <stdlib.h>
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

class Maze
{
public:
    Maze(long xdim, long ydim):
        xdim(xdim),
        ydim(ydim){
            this->init_bit_array((xdim * ydim));
        }
    ~Maze(){
        free(this->maze);
    };

    inline void bit_on(long xidx, long yidx){
        long index = (xdim * yidx) + xidx;
        this->maze[index>>3] |= bitmask[index%8];
    }

    inline void bit_off(long xidx, long yidx){
        long index = (xdim * yidx) + xidx;
        this->maze[index>>3] &= !bitmask[index%8];
    }

    inline bool check_bit(long xidx, long yidx){
        long index = (xdim * yidx) + xidx;
        return ((this->maze[index>>3]&bitmask[index%8]) == bitmask[index%8]);
    }

private:
    void init_bit_array(long size){
        long bit_array_size = (size/8)+1;
        this->maze = (unsigned char *) malloc(sizeof(char)*bit_array_size);//new unsigned char[bit_array_size];
        for(long i=0; i<bit_array_size; i++){
            this->maze[i] = BIT_CLEAR;
        }
        return;
    }
    unsigned char *maze;
    long xdim, ydim;
};

int main(int argc, char const *argv[])
{
    Maze laberinto(10,10);
    std::cout << "Laberinto inicializado" << std::endl;

    laberinto.bit_on(1,1);
    laberinto.bit_on(2,2);
    laberinto.bit_on(3,3);
    laberinto.bit_on(4,4);
    laberinto.bit_on(5,5);
    laberinto.bit_on(6,6);
    laberinto.bit_on(9,9);

    for (int i = 0; i < 10; ++i)
    {
        for (int j = 0; j < 10; ++j)
        {
            std::cout << (laberinto.check_bit(i,j));
        }
        std::cout << std::endl;
    }

    return 0;
}