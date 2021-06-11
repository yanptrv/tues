package com.home.Kris;

import java.util.Map;

public class Dish {
    private final String name;
    private final Map<Product, Double> productsForDish;
    private final int timeToCook;

    public Dish(String name, Map<Product, Double> productsForDish, int timeToCook) {
        this.name = name;
        this.productsForDish = productsForDish;
        this.timeToCook = timeToCook;
    }

    public double getSalePrice() {
        double price = 0;
        for (Product product : productsForDish.keySet()) {
            price += product.getPrice() * productsForDish.get(product);
        }
        return price*2;
    }

    public int getTimeToCook() {
        return timeToCook;
    }

    public String getName() {
        return name;
    }
}
