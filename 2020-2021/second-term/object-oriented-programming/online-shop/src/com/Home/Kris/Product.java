package com.Home.Kris;

import java.util.Optional;

public class Product {
    private final int ID = 0;
    private final String name;
    private final double price;

    private enum Unit {
        Gallon,
        Pound
    }
    private Unit unit;

    public Product(String name, double price, String unit) {
        try {
            if (unit.equals("Gallon") || unit.equals("Pound")){
                this.unit = Unit.valueOf(unit);
            }
            else {
                throw new Exception("Invalid unit argument");
            }
        } catch (Exception e) {
            e.printStackTrace();
            }
        this.name = name;
        this.price = price;
    }

    public Optional<Unit> getUnit() {
        return Optional.of(unit);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
