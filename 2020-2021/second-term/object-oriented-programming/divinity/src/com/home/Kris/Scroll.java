package com.home.Kris;

public class Scroll extends Item implements Usable, Equippable{
    private final int damage;

    public Scroll(String name, int damage) throws Exception {
        if (damage <= 0) {
            throw new Exception("Invalid scroll damage");
        }
        else {
            this.damage = damage;
            this.setName(name);
        }
    }

    @Override
    public void use(Character source, Character target) throws Exception {
        int dealtDamage;
        if (source.getIntelligence() < target.getIntelligence()) {
            dealtDamage = damage - ((target.getIntelligence() - source.getIntelligence()) * 10 * damage / 100);
            if (dealtDamage < 0) {
                throw new Exception("Your intelligence is too low");
            }
            else {
                target.setCurrentHealth(target.getCurrentHealth() - dealtDamage);
            }
        }
        else if (source.getIntelligence() > target.getIntelligence()) {
            dealtDamage = (source.getIntelligence() - target.getIntelligence()) * 10 * damage / 100;
            target.setCurrentHealth(target.getCurrentHealth() - dealtDamage);
        }
        else if (source.getIntelligence() == target.getIntelligence()) {
            dealtDamage = damage;
            target.setCurrentHealth(target.getCurrentHealth() - dealtDamage);
        }
    }
}
