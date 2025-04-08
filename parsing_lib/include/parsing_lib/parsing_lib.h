#pragma once

#ifdef _WIN32
  #ifdef PARSING_LIB_EXPORTS
    #define PARSING_LIB_API __declspec(dllexport)
  #else
    #define PARSING_LIB_API __declspec(dllimport)
  #endif
#else
  #define PARSING_LIB_API
#endif

#include <vector>
#include <string>

namespace parsing_lib {
    PARSING_LIB_API std::vector<std::string> tokenize(const std::string& expr);
    PARSING_LIB_API bool isOperator(const std::string& token);
    PARSING_LIB_API int getPrecedence(const std::string& op);
    PARSING_LIB_API bool isOperand(const std::string& token);
    PARSING_LIB_API std::vector<std::string> toPostfix(const std::vector<std::string>& tokens);
    PARSING_LIB_API void printPostfix(const std::vector<std::string>& postfix);
    PARSING_LIB_API void parseExpression(const std::string& expr);
}