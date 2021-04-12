package com.home.Kris;

import java.util.ArrayList;

public abstract class Character {
    private int currentHealth;
    private int maxHealth;
    private int intelligence;
    private int dexterity;
    private final ArrayList<Item> inventory = new ArrayList<>();
    private final ArrayList<Item> hotbar = new ArrayList<>();

    abstract protected String getCharacterClass();
    public String toString() {
        if (this.getCharacterClass().equals("Mage")){
            return "Class: " + getCharacterClass() + " Current HP: " + currentHealth + " Current Mana: " + ((Mage) this).getCurrentMana();
        }
        else {
            return "Class: " + getCharacterClass() + " Current HP: " + currentHealth;
        }
    }

    public void pick(Item newItem) throws Exception {
        if (newItem instanceof Equippable && hotbar.size() < 3) {
            hotbar.add(newItem);
        }
        else if (inventory.size() < 10){
            inventory.add(newItem);
        }
        else {
            throw new Exception("No more space");
        }
    }

    public void useAt(int index, Character target) throws Exception {
        if (index > hotbar.size()-1 || index < 0) {
            throw new Exception("No such item in the hotbar");
        }
        else {
            Item inUse = hotbar.get(index);
            if (inUse instanceof Consumable) {
                ((Consumable) inUse).consume(this);
            }
            else if (inUse instanceof Usable) {
                ((Usable) inUse).use(this, target);
            }
        }
    }

    public void setCurrentHealth(int currentHealth) throws Exception {
        if (currentHealth < 0 || currentHealth > getMaxHealth()) {
            throw new Exception("Invalid current health value");
        }
        else {
            this.currentHealth = currentHealth;
        }
    }

    public void setMaxHealth(int maxHealth) throws Exception {
        if (maxHealth <= 0) {
            throw new Exception("Invalid max health value");
        }
        else {
            this.maxHealth = maxHealth;
        }
    }

    public void setIntelligence(int intelligence) throws Exception {
        if (intelligence < 0) {
            throw new Exception("Invalid intelligence value");
        }
        else {
            this.intelligence = intelligence;
        }
    }

    public void setDexterity(int dexterity) throws Exception {
        if (dexterity < 0) {
            throw new Exception("Invalid dexterity value");
        }
        else {
            this.dexterity = dexterity;
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDexterity() {
        return dexterity;
    }
}
