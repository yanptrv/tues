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
        Point one = new Point(getX()+this.d, getY()+this.d);
        Point two = new Point(getX()+this.d, getY()-this.d);
        Point three = new Point(getX()-this.d, getY()-this.d);
        Point four = new Point(getX()-this.d, getY()+this.d);
        points.add(one);
        points.add(two);
        points.add(three);
        points.add(four);
        return points;
    }

    @Override
    public boolean checkCollision(Shape other) {
        for(Point point : this.getPoints()) {
            if(other.getX()-this.d <= point.getX()) {
                if(point.getX() <= other.getX()+this.d) {
                    if(other.getY()-this.d <= point.getY()) {
                        return point.getY() <= other.getY() + this.d;
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
