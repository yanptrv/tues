package com.home.Kris;

public class Archer extends Character {
    @Override
    protected String getCharacterClass() {
        return "Archer";
    }

    public Archer() {
        setCurrentHealth(90);
        setMaxHealth(90);
        setDexterity(20);
        setIntelligence(10);
    }
}
