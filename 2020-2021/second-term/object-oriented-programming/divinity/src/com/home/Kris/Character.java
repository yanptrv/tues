package com.home.Kris;

public abstract class Character {
    private int currentHealth;
    private int maxHealth;
    private int intelligence;
    private int dexterity;

    abstract protected String getCharacterClass();

    public String toString() {
        return "Class: " + getCharacterClass() + " Current HP: " + currentHealth + "\n";
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
}
