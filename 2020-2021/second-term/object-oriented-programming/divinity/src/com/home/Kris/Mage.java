package com.home.Kris;

public class Mage extends Character{
    @Override
    protected String getCharacterClass() {
        return "Mage";
    }

    private int maxMana;
    private int currentMana;

    public Mage() throws Exception {
        setMaxMana(35);
        setCurrentMana(35);
        setMaxHealth(70);
        setCurrentHealth(70);
        setDexterity(10);
        setIntelligence(25);
    }

    public void setMaxMana(int maxMana) throws Exception {
        if (maxMana <= 0) {
            throw new Exception("Invalid max mana value");
        }
        else {
            this.maxMana = maxMana;
        }
    }

    public void setCurrentMana(int currentMana) throws Exception {
        if (currentMana < 0 || currentMana > getMaxMana()) {
            throw new Exception("Invalid current mana value");
        }
        else {
            this.currentMana = currentMana;
        }
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }
}
