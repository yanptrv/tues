package com.home.Kris;

public class Card {
    private final String id;
    private int trainingsLeft;

    public Card(String id) {
        this.id = id;
        this.trainingsLeft = 10;
    }

    public void useCard() {
        this.trainingsLeft--;
    }

    public String getId() {
        return id;
    }
}
