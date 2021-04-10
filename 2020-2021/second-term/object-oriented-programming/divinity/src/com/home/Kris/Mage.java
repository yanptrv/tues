package com.home.Kris;

public class Mage extends Character{
    @Override
    protected String getCharacterClass() {
        return "Mage";
    }

    private int maxMana;
    private int currentMana;

    public Mage() {
        setMaxMana(35);
        setMaxHealth(35);
        setCurrentHealth(70);
        setMaxHealth(70);
        setDexterity(10);
        setIntelligence(25);
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }
}
