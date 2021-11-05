package com.home.Kris;

public class Potion extends Item implements Consumable, Equippable{
    private final int health;
    private final int mana;

    public Potion(String name, int health, int mana) throws Exception {
        if (health <= 0 || mana <= 0) {
            throw new Exception("Invalid potion mana or health value");
        }
        else {
            this.setName(name);
            this.health = health;
            this.mana = mana;
        }
    }

    @Override
    public void consume(Character target) throws Exception {
        target.setCurrentHealth(Math.min(target.getCurrentHealth() + health, target.getMaxHealth()));
        if (target instanceof Mage) {
            ((Mage) target).setCurrentMana(Math.min(((Mage) target).getCurrentMana() + mana, ((Mage) target).getMaxMana()));
        }
    }

}
