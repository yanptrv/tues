package com.home.Kris;

import java.util.ArrayList;
import java.util.List;

public class Square extends Shape implements PointsList, Collision{
    private final double d;

    public Square(double x, double y, double d) {
        super(x, y);
        this.d = d;
    }

    @Override
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        Point one = new Point(center.getX()+d, center.getY()+d);
        Point two = new Point(center.getX()+d, center.getY()-d);
        Point three = new Point(center.getX()-d, center.getY()-d);
        Point four = new Point(center.getX()-d, center.getY()+d);
        points.add(one);
        points.add(two);
        points.add(three);
        points.add(four);
        return points;
    }

    @Override
    public boolean checkCollision(Shape other) {
        for(Point point : this.getPoints()) {
            if(other.center.getX()-this.d <= point.getX()) {
                if(point.getX() <= other.center.getX()+this.d) {
                    if(other.center.getY()-this.d <= point.getY()) {
                        return point.getY() <= other.center.getY() + this.d;
                    }
                    else { return false; }
                }
                else { return false; }
            }
            else { return false; }
        }
        return false;
    }
}
