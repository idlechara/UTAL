#include <iostream>
#include <map>
#include <sstream>
#include <cctype>
#include <boost/algorithm/string.hpp>
using namespace std;


int main() {

    std::string s = "owo no quiero uwu";
    std::string key = "awade";
    std::stringstream ss_skey;

    boost::to_upper(s);
    boost::to_upper(key);

    std::string alphabet = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    // generates lkt
    std::map<char> lkt;


    for(int i=0; i<s.length(); i++){
        ss_skey << key.at(i % key.length());
    }
    std::string skey = ss_skey.str();

    std::cout << s << "\n";
    std::cout << skey << "\n";
    std::cout << alphabet.length() << "\n";


}


