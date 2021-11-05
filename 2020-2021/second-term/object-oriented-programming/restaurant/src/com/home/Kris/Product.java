package com.home.Kris;

public class Product {
    private final String name;
    private final double price;
    private final Unit productUnit;


    public Product(String name, double price, Unit productUnit) {
        this.name = name;
        this.price = price;
        this.productUnit = productUnit;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Unit getProductUnit() {
        return productUnit;
    }
}
