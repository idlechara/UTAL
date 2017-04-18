#include <iostream>
#include <cstdlib>
using namespace std;

int main(int argc, char const *argv[])
{
    int special     = 10;
    int normal      = 20;
    int max_slots   = 20;
    int tests       = 200;

    // cout << "Estacionamientos especiales: ";
    cin >> special;
    // cout << "Estacionamientos totales: ";
    cin >> normal;
    // cout << "Subterraneos totales: ";
    cin >> max_slots;

    if(normal < special) normal = special;

    int total = normal * max_slots;

    // cout << "Ingresos a bitácora: ";
    cin >> tests;

    for(int i=0; i < tests; i++){
        int choice = rand() % 4;
        switch(choice){
            case 1:
                int floor_ = rand() % max_slots + 1;
                int slot_ = rand() % normal + 1;
                cout << "S -" << floor_ << " " << slot_ << " ";
            defaut:
                int type_n = rand() % 2;
                cout << "I " << ((type_n == 0) ? "n" : "e");
                cout << " ";
        }
    }

    return 0;
}