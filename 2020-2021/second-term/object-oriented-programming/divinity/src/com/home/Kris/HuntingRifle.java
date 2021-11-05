package com.home.Kris;

public class HuntingRifle extends Item implements  Usable, Equippable{
    private final int damage;

    public HuntingRifle(String name, int damage) throws Exception {
        if (damage <= 0) {
            throw new Exception("Invalid hunting rifle damage");
        }
        else {
            this.damage = damage;
            this.setName(name);
        }
    }

    @Override
    public void use(Character source, Character target) throws Exception {
        int dealtDamage;
        if (source.getDexterity() < target.getDexterity()) {
            dealtDamage = damage - ((target.getDexterity() - source.getDexterity()) * 10 * damage / 100);
            if (dealtDamage < 0) {
                throw new Exception("Your dexterity is too low");
            }
            else {
                target.setCurrentHealth(target.getCurrentHealth() - dealtDamage);
            }
        }
        else if (source.getDexterity() > target.getDexterity()) {
            dealtDamage = (source.getDexterity() - target.getDexterity()) * 10 * damage / 100;
            target.setCurrentHealth(target.getCurrentHealth() - dealtDamage);
        }
        else if (source.getDexterity() == target.getDexterity()) {
            dealtDamage = damage;
            target.setCurrentHealth(target.getCurrentHealth() - dealtDamage);
        }

    }
}
