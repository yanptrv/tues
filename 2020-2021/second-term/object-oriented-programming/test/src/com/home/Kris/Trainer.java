package com.home.Kris;

public class Trainer {
    private final int id;
    private final String name;

    public int getId() {
        return id;
    }

    public Trainer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
