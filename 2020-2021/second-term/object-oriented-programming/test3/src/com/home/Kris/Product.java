package com.home.Kris;

public class Product {
    private final String name;
    private int maxAmount;

    public Product(String name) throws Exception {
        if (name == null || name.isBlank()) {
            throw new Exception("Invalid name");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMaxAmount(int maxAmount) throws Exception {
        if (maxAmount <= 0) {
            throw new Exception("Invalid max amount");
        }
        this.maxAmount = maxAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }
}
