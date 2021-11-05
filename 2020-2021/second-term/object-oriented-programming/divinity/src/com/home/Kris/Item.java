package com.home.Kris;

public abstract class Item {
    private String name;

    public void setName(String name) throws Exception {
        if (name.equals("")) {
            throw new Exception("Invalid name");
        }
        else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }
}
