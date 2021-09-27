#include "vector_math.h"

Vector::Vector(Point p1, Point p2)
{
    this->x = p2.x - p1.x;
    this->y = p2.y - p1.y;
}

void Vector::sum(const Vector& other) const
{
    std::cout << "(" << this->x + other.x << ", " << this->y + other.y << ")" << std::endl;
}

void Vector::difference(const Vector& other) const
{
    std::cout << "(" << this->x - other.x << ", " << this->y - other.y << ")" << std::endl;
}

bool Vector::is_colinear(const Vector& other) const
{
    return this->x / other.x == this->y / other.y;
}

double Vector::length() const
{
    return sqrt(this->x * this->x + this->y * this->y);
}

double Vector::angle(const Vector& other) const
{
    return acos((this->x * other.x + this->y * other.y) / (length() * other.length()));
}

double Vector::area(const Point& p1, const Point& p2, const Point& p3)
{
    double a = 1/2*((p2.x-p1.x)*(p3.y-p1.y)-(p3.x-p1.x)*(p2.y-p1.y));
    if (a < 0)
    {
        return -a;
    }
    else
    {
        return a;
    }

}