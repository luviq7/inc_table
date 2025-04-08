#include "parsing_lib/parsing_lib.h"
#include <iostream>
#include <vector>
#include <string>
#include <stack>
#include <unordered_set>
#include <algorithm>
#include <unordered_map>

void generateTruthTable(const std::string& expr) {
    std::vector<std::string> tokens = parsing_lib::tokenize(expr);

    std::unordered_set<std::string> operandsSet;
    for (const auto& token : tokens) {
        if (parsing_lib::isOperand(token)) {
            operandsSet.insert(token);
        }
    }

    std::vector<std::string> operands(operandsSet.begin(), operandsSet.end());
    std::sort(operands.begin(), operands.end());

    size_t n = operands.size();
    size_t rows = 1 << n; 
    for (const auto& operand : operands) {
        std::cout << operand << " ";
    }
    std::cout << "Result" << std::endl;

    for (size_t i = 0; i < rows; ++i) {
        std::unordered_map<std::string, bool> valueMap;
        for (size_t j = 0; j < n; ++j) {
            valueMap[operands[j]] = (i >> (n - j - 1)) & 1;
            std::cout << valueMap[operands[j]] << " ";
        }

        std::vector<std::string> postfix = parsing_lib::toPostfix(tokens);
        std::stack<bool> resultStack;

        for (const auto& token : postfix) {
            if (parsing_lib::isOperand(token)) {
                resultStack.push(valueMap[token]);
            } 
            else if (token == "!") {
                bool a = resultStack.top(); resultStack.pop();
                resultStack.push(!a);
            } 
            else {
                bool b = resultStack.top(); resultStack.pop();
                bool a = resultStack.top(); resultStack.pop();
                
                if (token == "&&") resultStack.push(a && b);
                else if (token == "||") resultStack.push(a || b);
                else if (token == "->") resultStack.push(!a || b);
                else if (token == "<->") resultStack.push(a == b);
            }
        }

        std::cout << resultStack.top() << std::endl;
    }
}

int main() {
    std::string expr = "((p->(q&&r))∣∣(!q->(s&&t)))->((p->!r)∣∣(q->s))";
    std::cout << "Input Expression: " << expr << std::endl;
    
    std::vector<std::string> tokens = parsing_lib::tokenize(expr);
    std::cout << "Tokens: ";
    for (const auto& t : tokens) std::cout << t << " ";
    std::cout << std::endl;
    
    std::vector<std::string> postfix = parsing_lib::toPostfix(tokens);
    std::cout << "Postfix: ";
    for (const auto& t : postfix) std::cout << t << " ";
    std::cout << std::endl;
    
    generateTruthTable(expr);
    return 0;
}