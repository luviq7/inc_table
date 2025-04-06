#include "parsing_lib/parsing_lib.h"
#include <iostream>
#include <vector>
#include <string>

int main() {
    std::string expr = "((p->q)&&(!(!(q)->r)))<->(p->(!(r)))||x";
    
    std::vector<std::string> tokens = parsing_lib::tokenize(expr);

    std::cout << "Tokens: ";

    for (const std::string& token : tokens) {
        std::cout << token << " ";
    }
    std::cout << "\n";
}
