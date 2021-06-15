package com.home.Kris;

public class Helper implements Runnable{
    private final String name;
    private final Restaurant myRestaurant;

    public Helper(String name, Restaurant myRestaurant) throws Exception {
        if (name == null || myRestaurant == null || name.isBlank()) {
            throw new Exception("Invalid arguments");
        }
        this.name = name;
        this.myRestaurant = myRestaurant;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {

    }

    public Restaurant getMyRestaurant() {
        return myRestaurant;
    }
}
