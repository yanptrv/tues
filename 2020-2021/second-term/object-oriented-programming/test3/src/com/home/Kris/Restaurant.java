package com.home.Kris;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Restaurant {
    private final String name;
    private Map<Product, Integer> products;
    private Set<Dish> dishes = new HashSet<>();

    public Restaurant(String name) throws Exception {
        if (name == null || name.isBlank()) {
            throw new Exception("Invalid name");
        }
        this.name = name;
    }

    public void addProduct(Product product, int total) throws Exception {
        if (product == null || total < 0) {
            throw new Exception("Invalid arguments");
        }
        if (products.containsKey(product)) {
            throw new Exception("Such product already exists");
        } else {
            products.put(product, 0);
            product.setMaxAmount(total);
        }
    }

    public void useProduct(Product product, int amount) throws Exception {
        if (product == null || amount < 0) {
            throw new Exception("Invalid arguments");
        }
        if (products.containsKey(product)) {
            if (products.get(product) - amount < 0) {
                throw new Exception("Don't have that much amount");
            } else {
                products.put(product, products.get(product) - amount);
            }
        } else {
            throw new Exception("No such product");
        }
    }

    public void loadProduct(Product product, int amount) throws Exception {
        if (product == null || amount < 0) {
            throw new Exception("Invalid arguments");
        }
        if (products.containsKey(product)) {
            if (products.get(product)+amount > product.getMaxAmount()) {
                throw new Exception("Too much product");
            } else {
                products.put(product, products.get(product) + amount);
            }
        } else {
            throw new Exception("No such product");
        }
    }

    public int getProductAmount(Product product) {
        return products.get(product);
    }

    public int getProductMaxAmount(Product product) {
        return product.getMaxAmount();
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public String getName() {
        return name;
    }
}
