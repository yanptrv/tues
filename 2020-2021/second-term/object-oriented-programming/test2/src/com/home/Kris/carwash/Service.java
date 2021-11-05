package com.home.Kris.carwash;

public class Service {
    private final String name;
    private final double price;

    public Service(String name, double price) throws Exception {
        if (name.isEmpty() || name.isBlank()){
            throw new Exception("Invalid name!");
        }
        else {
            this.name = name;
        }
        if(price <= 0){
            throw new Exception("Invalid price");
        }
        else {
            this.price = price;
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
