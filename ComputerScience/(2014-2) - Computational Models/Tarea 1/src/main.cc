#include <iostream>
#include <fstream>
#include <string>
#include "Automata.cc"
// #include <unordered_set>

// //THIS ONLY WORKS  ON OLD VERSION, CURRENT APPROACH IS AN INCREMENTAL ONE
// bool is_operator(char target){
//     return !((target >= 48 && target < 58) || (target >= 65 && target < 91) || (target >= 97 && target < 123));
// }

// int find_first(std::string &str, char target){
//     for (int i = 0; i < str.length(); ++i){
//         if(str[i] == target)
//             return i;
//     }
//     return -1;
// }

// int find_first_operator(std::string &str, int idx){
//     for (int i = idx; i < str.length(); ++i){
//         if(is_operator(str[i]))
//             return i;
//     }
//     return -1;
// }

// std::string get_lhs(std::string &str, int oidx){
//     int length = 0;
//     while( !is_operator(str[oidx-1]) ) {
//         oidx--;
//         length++;
//     }
//     return str.substr(oidx, length);
// }

// std::string get_rhs(std::string &str, int oidx){
//     int length = 0;
//     while( !is_operator(str[oidx+1]) ) {
//         oidx++;
//         length++;
//     }
//     return str.substr(oidx-length+1, length);
// }

// int main(int argc, char const *argv[])
// {
//     char temp = 0;

//     std::string input;
//     std::stack<char> pila_llamados;

//     std::cout << "inserte cadena a analizar" << std::endl;
//     std::cin >> input;

//     for (int i = 0; i < input.length(); ++i)
//     {
//         std::cout << "Character: " << input[i] << "\t ASCII: " << (int)input[i] << "\t OPERATOR?: " << is_operator(input[i]) << std::endl;
//     }
//     std::cout << "LHS: " << get_lhs(input, find_first_operator(input,0)) << std::endl;
//     std::cout << "RHS: " << get_rhs(input, find_first_operator(input,0)) << std::endl;

//     std::cout <<  find_first_operator(input,0) << std::endl;
//     std::cout <<  find_first(input, '|') << std::endl;
//     return 0;
// }

int main(int argc, char const *argv[])
{
    // std::string data;
    // std::cin >> data;
    // data = std::string("reg(ex)*");
    // std::cout << "Okay, I'm good\n";
    std::ifstream input(argv[1]);
    Automata kuhkuhkuh(argv[2]);
    for( std::string line; getline( input, line ); ){    

        // std::cout<< "comparing:" << line << "\n";
        if(kuhkuhkuh.run(std::string(line)))
            std::cout << line << "\n";
    }


    return 0;
}