add_executable(my_app
    src/main.cpp
)

target_link_libraries(my_app PRIVATE parsing_lib)

target_include_directories(my_app PRIVATE
    ${PROJECT_SOURCE_DIR}/parsing_lib/include
)
