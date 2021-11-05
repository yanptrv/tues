package com.home.Kris;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private final Map<Product, Double> productsInStorage = new HashMap<>();

    public void addProduct(Product product, double amount) {
        productsInStorage.merge(product, amount, Double::sum);
        System.out.println(productsInStorage.get(product));
    }

    public void getProduct(Product product, double amount) throws Exception {
        if (productsInStorage.get(product) == null) {
            throw new Exception("No such product in storage");
        }
        double checkAmount = productsInStorage.get(product) - amount;
        if (checkAmount >= 0) {
            productsInStorage.put(product, checkAmount);
        }
        else throw new Exception("No such amount in storage");
        System.out.println(productsInStorage.get(product));
    }
}
