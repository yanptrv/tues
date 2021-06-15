package com.home.Kris;

import java.util.HashMap;
import java.util.Map;

public class Dish {
    private final String name;
    private final Map<Product, Integer> products;
    private final int time;

    public Dish(String name, Map<Product, Integer> products, int time) throws Exception {
        if (products == null || name == null || name.isBlank()|| products.isEmpty() || time <= 0) {
            throw new Exception("Invalid arguments");
        }
        this.name = name;
        this.products = products;
        this.time = time;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }
}
