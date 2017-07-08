/* (c) 2014 - Erik Regla Torres
 *
 *  This file is part of LSM303DLHC.
 *
 *  LSM303DLHC is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  LSM303DLHC is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with LSM303DLHC. If not, see <http://www.gnu.org/licenses/>.
 */

#include <iostream>
#include <stdlib.h>
#include <unistd.h>
#include <linux/i2c.h>
#include <linux/i2c-dev.h>
#include <sys/ioctl.h>
#include <fcntl.h>
#include <string.h>
#include <stdio.h>
#include "lib/network/Client.h"

#include "lib/LSM303DLHC.h"

using namespace std;

void calculate(double scale, lsm303_t *current, lsm303_t *max, lsm303_t *min, lsm303_t *zero, lsm303_t *range, lsm303_t *temp) {
    //calculate limits
    if (current->x > max->x)
        max->x = current->x;
    if (current->y > max->y)
        max->y = current->y;
    if (current->z > max->z)
        max->z = current->z;

    if (current->x < min->x)
        min->x = current->x;
    if (current->y < min->y)
        min->y = current->y;
    if (current->z < min->z)
        min->z = current->z;

    //calculate zero
    zero->x = (min->x + max->x) / 2;
    zero->y = (min->y + max->y) / 2;
    zero->z = (min->z + max->z) / 2;

    //get range
    range->x = zero->x - min->x;
    range->y = zero->y - min->y;
    range->z = zero->z - min->z;

    //get current value
    temp->x = current->x - zero->x;
    temp->y = current->y - zero->y;
    temp->z = current->z - zero->z;

    current->x = (temp->x / range->x) * scale;
    current->y = (temp->y / range->y) * scale;
    current->z = (temp->z / range->z) * scale;

}

int main(int argc, char **argv) {
    cout << "Connecting to " << argv[1] << ":" << argv[2]<< "..." << endl;
    cout << "Sampling speed: " << argv[3] << "microseconds" << endl;
    Client client(argv[1], atoi(argv[2]));
    cout << " Done." << endl << "Press [ENTER] to start polling" << endl;
//    cout << "Reading magnetometer data... press [ENTER] to read" << endl;
    LSM303DLHC sensor("/dev/i2c-1");
    sensor.init_magnetometer();
    sensor.init_accelerometer();
    lsm303_t data, max_data, min_data, zero, range, temp;

    sensor.read_magnetometer(&data);
    sensor.read_magnetometer(&min_data);
    sensor.read_magnetometer(&max_data);

    calculate(1, &data, &max_data, &min_data, &zero, &range, &temp);


    cin.ignore();

    while (1) {
        sensor.read_magnetometer(&data);

//        data.x *= 1000;
//        data.y *= 1000;
//        data.z *= 1000;

        //get a vector
        calculate(1, &data, &max_data, &min_data, &zero, &range, &temp);

        client.send_to_server(&data);
        usleep( atoi(argv[3]));
//        cout << ".";
//
//        cout << "MAG: x:" << data.x << " y:" << data.y << " z:" << data.z << endl;
//        sensor.read_accelerometer(&data);
//        cout << "ACC: x:" << data.x  << " y:" << data.y  << " z:" << data.z  << endl;
//        cin.ignore();
    }
    return 0;
}
