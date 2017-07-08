#ifndef DEBUGDATA_H
#define DEBUGDATA_H
#include <semaphore.h>
#include <string>
#include <LSM303DLHC.h>

class DebugData
{

private:
    sem_t semaphore;
    std::string output_string;
    void *image;
    unsigned int image_size;
    std::magdata_t accelerometer_data;
    std::magdata_t magnetometer_data;
public:
    DebugData();
    void get_output_string(std::string target);
    void get_image(void *data, unsigned int *size);
    void get_accelerometer_data(std::magdata_t target);
    void get_magnetometer_data(std::magdata_t target);
    void set_output_string(std::string target);
    void set_image(void *data, int size);
    void set_accelerometer_data(std::magdata_t target);
    void Set_magnetometer_data(std::magdata_t target);
};

#endif // DEBUGDATA_H
