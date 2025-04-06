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

}
