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

class BitArray
{
public:
    BitArray(unsigned long size){
        long bit_array_size = (size/8)+1;
        this->data = new unsigned char[bit_array_size];    
        this->lenght = size;
        for(long i=0; i<bit_array_size; i++){
            this->data[i] = BIT_CLEAR;
        }    
    };

    ~BitArray();
    void on(unsigned long idx){
        this->data[idx>>3] |= bitmask[idx%8];
    };
    void off(unsigned long idx){
        this->data[idx>>3] &= !bitmask[idx%8];
    };
    bool check_bit(unsigned long idx){
        return ((this->data[idx>>3]&bitmask[idx%8]) == bitmask[idx%8]);    
    };

private:
    char *data;
    unsigned long lenght;
    unsigned char bitmask[8] = {BIT_0, BIT_1, BIT_2, BIT_3, BIT_4, BIT_5, BIT_6, BIT_7};


};