package com.home.Kris;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Square sq1 = new Square(10,5, 3);
        Square sq2 = new Square(9,4,2);
        for(Point point : sq1.getPoints()) { System.out.println(point.getX() + " " + point.getY()); }
        System.out.println(sq1.checkCollision(sq2));
    }
}
