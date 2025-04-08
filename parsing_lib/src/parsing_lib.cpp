#include "parsing_lib/parsing_lib.h"
#include <iostream>
#include <cctype>
#include <stack>

namespace parsing_lib {

    std::vector<std::string> tokenize(const std::string& expr) {
        std::vector<std::string> tokens;
        std::string current_token;
        size_t i = 0;
        while (i < expr.length()) {
            char ch = expr[i];
            if (isspace(ch)) {
                i++;
                continue;
            }
            if (i + 1 < expr.length()) {
                if (expr[i] == '-' && expr[i + 1] == '>') {
                    tokens.push_back("->");
                    i += 2;
                    continue;
                }
                if (expr[i] == '<' && expr[i + 1] == '-' && expr[i + 2] == '>') {
                    tokens.push_back("<->");
                    i += 3;
                    continue;
                }
                if (expr[i] == '&' && expr[i + 1] == '&') {
                    tokens.push_back("&&");
                    i += 2;
                    continue;
                }
                if (expr[i] == '|' && expr[i + 1] == '|') {
                    tokens.push_back("||");
                    i += 2;
                    continue;
                }
            }
            if (ch == '!') {
                tokens.push_back("!");
                i++;
                continue;
            }
            if (isalnum(ch)) {
                current_token.clear();
                while (i < expr.length() && isalnum(expr[i])) {
                    current_token.push_back(expr[i]);
                    i++;
                }
                tokens.push_back(current_token);
                continue;
            }
            if (ch == '(' || ch == ')') {
                tokens.push_back(std::string(1, ch));
                i++;
                continue;
            }
            i++;
        }

        return tokens;
    }

    bool isOperator(const std::string& token) {
        return token == "&&" || token == "||" || token == "->" || token == "<->" || token == "!" ;
    }

    int getPrecedence(const std::string& op) {
        if (op == "!") return 3;
        if (op == "&&") return 2;
        if (op == "||") return 1;
        if (op == "->" || op == "<->") return 0;
        return -1;
    }

    bool isLeftAssociative(const std::string& op) {
        return op != "!";
    }

    bool isOperand(const std::string& token) {
        return !isOperator(token) && token != "(" && token != ")";
    }

    std::vector<std::string> toPostfix(const std::vector<std::string>& tokens) {
        std::stack<std::string> operatorStack;
        std::vector<std::string> output;
    
        for (const auto& token : tokens) {
            if (isOperand(token)) {
                output.push_back(token);
            } 
            else if (token == "(") {
                operatorStack.push(token);
            } 
            else if (token == ")") {
                while (!operatorStack.empty() && operatorStack.top() != "(") {
                    output.push_back(operatorStack.top());
                    operatorStack.pop();
                }
                operatorStack.pop(); 
            } 
            else if (isOperator(token)) {
                while (!operatorStack.empty() && 
                       (getPrecedence(operatorStack.top()) > getPrecedence(token) || 
                        (getPrecedence(operatorStack.top()) == getPrecedence(token) && isLeftAssociative(token)))) {
                    output.push_back(operatorStack.top());
                    operatorStack.pop();
                }
                operatorStack.push(token);
            }
        }
    
        while (!operatorStack.empty()) {
            output.push_back(operatorStack.top());
            operatorStack.pop();
        }
    
        return output;
    }
    
    void printPostfix(const std::vector<std::string>& postfix) {
        for (const auto& token : postfix) {
            std::cout << token << " ";
        }
        std::cout << std::endl;
    }

    void parseExpression(const std::string& expr) {
        std::vector<std::string> tokens = tokenize(expr);
        std::vector<std::string> postfix = toPostfix(tokens);
        printPostfix(postfix);
    }
}