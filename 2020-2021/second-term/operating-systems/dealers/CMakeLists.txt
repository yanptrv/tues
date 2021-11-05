cmake_minimum_required(VERSION 3.17)
project(dealers C)

set(CMAKE_C_STANDARD 99)
set(CMAKE_C_FLAGS "-Wall -Werror -Wpedantic -std=c11 -lpthread")

add_executable(dealers main.c)