package com.home.Kris;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cashier implements Runnable{
    private final String name;
    private final Restaurant myRestaurant;

    public Cashier(String name, Restaurant restaurant) throws Exception {
        if (name == null || restaurant == null || name.isBlank()) {
            throw new Exception("Invalid arguments");
        }
        this.name = name;
        this.myRestaurant = restaurant;
    }

    public Note makeOrder(String... dishes) throws Exception {
        if (dishes == null) {
            throw new Exception("Invalid dishes");
        }
        Map<String, Integer> orderedDishes = new HashMap<>();
        Set<Dish> restaurantDishes = myRestaurant.getDishes();
        for (String dishName : dishes) {
            for (Dish dishRestaurant : restaurantDishes) {
                if (!dishName.equals(dishRestaurant.getName())) {
                    throw new Exception("No such dish in our restaurant");
                }
            }
        }
        for (String dish : dishes) {
            if (orderedDishes.containsKey(dish)) {
                orderedDishes.put(dish, orderedDishes.get(dish)+1);
            } else {
                orderedDishes.put(dish, 1);
            }
        }

        return new Note(orderedDishes);
    }

    @Override
    public void run() {

    }

    public String getName() {
        return name;
    }

    public Restaurant getRestaurant() {
        return myRestaurant;
    }
}
