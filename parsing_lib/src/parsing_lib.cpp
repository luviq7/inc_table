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

    
}
