package com.home.Kris;

public class Archer extends Character {
    @Override
    protected String getCharacterClass() {
        return "Archer";
    }

    public Archer() throws Exception {
        setMaxHealth(90);
        setCurrentHealth(90);
        setDexterity(20);
        setIntelligence(10);
    }
}
