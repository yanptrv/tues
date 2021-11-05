package com.home.Kris;

public class Clients {
    private final String firstName;
    private final String lastName;
    private Card card;

    public Clients(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getId() {
        return card.getId();
    }
}
