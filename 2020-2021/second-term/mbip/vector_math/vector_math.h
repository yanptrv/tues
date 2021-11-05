#include <bits/stdc++.h>

struct Point
{
    double x, y;      
};

class Vector
{
    private:
        double x, y;
    public:
        Vector (double, double);
        Vector (Point, Point);
        
        void sum(const Vector&) const;
        void difference(const Vector&) const;
        bool is_colinear(const Vector&) const;
        double length() const;
        double angle(const Vector&) const;
        double area(const Point&, const Point&, const Point&);
        double area(const Point points[50], int); 
};
