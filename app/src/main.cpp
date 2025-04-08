#include "parsing_lib/parsing_lib.h"
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <stack>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>
#include <sstream>
#include <filesystem>

void generateTruthTable(const std::string& expr, const std::string& outputFileName) {
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

    std::ofstream outputFile(outputFileName.c_str());  
    if (!outputFile.is_open()) {
        std::cerr << "Не вдалося відкрити файл для запису: " << outputFileName << std::endl;
        return;
    }

    outputFile << "Input Expression: " << expr << std::endl;


    for (const auto& operand : operands) {
        outputFile << operand << " ";
    }
    outputFile << "Result" << std::endl;

    for (size_t i = 0; i < rows; ++i) {
        std::unordered_map<std::string, bool> valueMap;
        for (size_t j = 0; j < n; ++j) {
            valueMap[operands[j]] = (i >> (n - j - 1)) & 1;
            outputFile << valueMap[operands[j]] << " ";
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

        outputFile << resultStack.top() << std::endl;
    }

    outputFile.close();
}

int main() {
    std::string inputFilePath = "../app/input/input_expressions.txt"; 
    std::string outputDirPath = "../app/output/"; 
    if (!std::filesystem::exists(outputDirPath)) {
        std::filesystem::create_directory(outputDirPath);
    }
    std::ifstream inputFile(inputFilePath);
    if (!inputFile.is_open()) {
        std::cerr << "Не вдалося відкрити файл: " << inputFilePath << std::endl;
        return 1;
    }
    std::string expr;
    size_t exprCount = 0;
    while (std::getline(inputFile, expr)) {
        exprCount++;
        std::cout << "Обробляю вираз " << exprCount << ": " << expr << std::endl;
        std::string outputFileName = outputDirPath + "truth_table_" + std::to_string(exprCount) + ".txt";
        generateTruthTable(expr, outputFileName); 
    }
    inputFile.close();
    return 0;
}


