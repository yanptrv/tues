package com.home.Kris;

public class Chef implements Runnable {
    private final String name;

    public Chef(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {

    }
}
