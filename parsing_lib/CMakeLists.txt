add_library(parsing_lib SHARED
    src/parsing_lib.cpp
)

target_include_directories(parsing_lib PUBLIC
    ${CMAKE_CURRENT_SOURCE_DIR}/include
)

target_compile_definitions(parsing_lib PRIVATE PARSING_LIB_EXPORTS)
