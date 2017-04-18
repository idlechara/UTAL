/*
 * LocalDebugServer.cpp
 *
 *  Created on: Mar 2, 2014
 *      Author: jvarred
 */

#include <iostream>
#include "Server.h"
using namespace std;

int main() {
    cout << "!!!Hello World!!!" << endl; // prints !!!Hello World!!!
    //Client client("localhost");
    Server servidor(4001);
    int client = servidor.wait_for_a_client();
    string data("hello!");
    servidor.send_to_client(client, data);
    servidor.recieve_from_client(client, data);
    cout << "Recieved:" << data << endl;
    servidor.close_client(client);
    cout << "Excecution terminated..." << endl;
    return 0;
}
