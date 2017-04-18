#include "debugdata.h"

DebugData::DebugData()
{
    sem_init(&this->semaphore, 0, 1);
    this->accelerometer_data.x=0;
    this->accelerometer_data.y=0;
    this->accelerometer_data.z=0;
    this->magnetometer_data.x=0;
    this->magnetometer_data.y=0;
    this->magnetometer_data.z=0;
    this->output_string = "";
    sem_post(&this->semaphore);
}

void DebugData::get_accelerometer_data(std::magdata_t *target){
    sem_wait(&this->semaphore);
    target = this->accelerometer_data;
    sem_post(&this->semaphore);
}

void DebugData::get_magnetometer_data(std::magdata_t *target){
    sem_wait(&this->semaphore);
    target = this->magnetometer_data;
    sem_post(&this->semaphore);
}

void DebugData::get_image(void *data, unsigned int *size){
    sem_wait(&this->semaphore);
    data = this->image;
    size = this->image_size
    sem_post(&this->semaphore);
}
