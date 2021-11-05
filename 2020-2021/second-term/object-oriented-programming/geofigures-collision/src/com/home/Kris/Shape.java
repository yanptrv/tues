package com.home.Kris;

public class Shape {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    Point center = new Point(x, y);

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
