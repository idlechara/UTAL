// Copyright 2020 Erik Regla
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Compile with :
 *      g++ main.cpp -o main
 * 
 * Execute with: 
 *      ./main
 * 
 * Input by STDIN
 * 
 * If too lazy, use VSCode as this code is OS agnostic, only requires a C++ compiler.
 **/

#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <bitset>
#include <numeric>

int main(int argc, char const *argv[]) {
    // setup random
    srand((unsigned)time(0));

    // Input string
    std::string input;
    std::vector<char> char_array;
    std::vector<std::bitset<8>> bit_array;
    std::vector<std::string> bit_array_s;

    // Key string
    std::vector<char> key_array;
    std::vector<std::bitset<8>> key_bit_array;
    std::vector<std::string> key_bit_array_s;

    // Encoded string
    std::vector<char> enc_array;
    std::vector<std::bitset<8>> enc_bit_array;
    std::vector<std::string> enc_bit_array_s;

    // Reconstructed string
    std::vector<char> reconstructed_array;
    std::vector<std::bitset<8>> reconstructed_bit_array;
    std::vector<std::string> reconstructed_bit_array_s;


    // Read line
    getline(std::cin, input);

    // Trim invalid characters
    for (size_t i_idx = 0; i_idx < input.size(); i_idx++) {
        bool uppercase = (65 <= input[i_idx] && input[i_idx] <= 90);
        bool lowercase = (97 <= input[i_idx] && input[i_idx] <= 122);

        if (uppercase || lowercase) {
            // Store original char
            char input_char = input[i_idx];
            char_array.push_back(input_char);
            std::bitset<8> bit_representation = (input_char);
            bit_array.push_back(bit_representation);
            bit_array_s.push_back(bit_representation.to_string()); bit_array_s.push_back(" ");

            // Store key representation
            char rand_char = rand();
            std::bitset<8> key_bit_representation = (rand_char);
            key_array.push_back(rand_char);
            key_bit_array.push_back(key_bit_representation);
            key_bit_array_s.push_back(key_bit_representation.to_string()); key_bit_array_s.push_back(" ");

            // Store encoded representation
            char enc_char = input_char ^ rand_char;
            std::bitset<8> enc_bit_representation = (enc_char);
            enc_array.push_back(enc_char);
            enc_bit_array.push_back(enc_bit_representation);
            enc_bit_array_s.push_back(enc_bit_representation.to_string()); enc_bit_array_s.push_back(" ");

            // Store encoded representation
            char reconstructed_char = enc_char ^ rand_char;
            std::bitset<8> reconstructed_bit_representation = (reconstructed_char);
            reconstructed_array.push_back(reconstructed_char);
            reconstructed_bit_array.push_back(reconstructed_bit_representation);
            reconstructed_bit_array_s.push_back(reconstructed_bit_representation.to_string()); reconstructed_bit_array_s.push_back(" ");
        }
    }
    
    std::cout << std::endl; std::string temp_string;
    std::cout << "Raw Input:     " << input << std::endl;
    std::cout << "ASCII Input:   " << std::string(char_array.begin(), char_array.end()) << std::endl;
    std::cout << "Binary Input:  " << std::accumulate(bit_array_s.begin(), bit_array_s.end(), temp_string) << std::endl;

    std::cout << std::endl; temp_string = "";
    std::cout << "ASCII Key:     " << std::accumulate(key_array.begin(), key_array.end(), temp_string) << std::endl; temp_string = "";
    std::cout << "Binary key:    " << std::accumulate(key_bit_array_s.begin(), key_bit_array_s.end(), temp_string) << std::endl;
    
    std::cout << std::endl; temp_string = "";
    std::cout << "ASCII Enc:     " << std::accumulate(enc_array.begin(), enc_array.end(), temp_string) << std::endl; temp_string = "";
    std::cout << "Binary Enc:    " << std::accumulate(enc_bit_array_s.begin(), enc_bit_array_s.end(), temp_string) << std::endl;
    
    std::cout << std::endl; temp_string = "";
    std::cout << "ASCII Rec:     " << std::accumulate(reconstructed_array.begin(), reconstructed_array.end(), temp_string) << std::endl; temp_string = "";
    std::cout << "Binary Rec:    " << std::accumulate(reconstructed_bit_array_s.begin(), reconstructed_bit_array_s.end(), temp_string) << std::endl;
    
    return 0;
}
