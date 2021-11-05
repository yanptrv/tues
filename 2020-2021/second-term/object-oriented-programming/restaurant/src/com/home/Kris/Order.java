package com.home.Kris;

import java.util.*;

public class Order {
    private final String id;
    private final Map<Dish, Double> orderedDishes;

    public Order(Map<Dish, Double> orderedDishes) {
        this.id = UUID.randomUUID().toString();
        this.orderedDishes = orderedDishes;
    }

    public String getId() {
        return id;
    }

    public Map<Dish, Double> getOrderedDishes() {
        return orderedDishes;
    }
}
