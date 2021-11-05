#include "vector_math.h"

int main (int agrc, char* argv[])
{
    Point p1;
    p1.x = 1;
    p1.y = 1;
    Point p2;
    p2.x = -3;
    p2.y = -2;
    Vector test(p1, p2);
    std::cout << test.length();
    
    return 0;
}