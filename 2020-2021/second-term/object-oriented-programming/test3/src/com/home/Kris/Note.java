package com.home.Kris;

import java.util.Map;

public class Note {
    private final Map<String, Integer> dishes;

    public Note(Map<String, Integer> dishes) throws Exception {
        if (dishes == null || dishes.isEmpty()) {
            throw new Exception("Invalid argument");
        }
        this.dishes = dishes;
    }

    public Map<String, Integer> getDishes() {
        return dishes;
    }
}
