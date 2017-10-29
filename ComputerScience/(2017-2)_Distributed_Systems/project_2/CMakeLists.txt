cmake_minimum_required(VERSION 3.8)
project(project_2)

set(CMAKE_CXX_STANDARD 17)

#set(SOURCE_FILES main.c slave.c clean.c)
set(SOURCE_FILES main.c)
add_executable(project_2 ${SOURCE_FILES})