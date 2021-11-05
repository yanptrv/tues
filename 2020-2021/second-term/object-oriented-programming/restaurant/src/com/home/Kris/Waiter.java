package com.home.Kris;

public class Waiter implements Runnable {
    private final String name;

    public Waiter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {

    }
}
