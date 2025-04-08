#include "parsing_lib/parsing_lib.h"
#include <iostream>
#include <vector>
#include <string>

int main() {
    std::string expr = "(p->q) && (!(q->r)) <-> (p->!(r))";

    std::cout << "Input Expression: " << expr << std::endl;


    parsing_lib::parseExpression(expr);

    return 0;
}