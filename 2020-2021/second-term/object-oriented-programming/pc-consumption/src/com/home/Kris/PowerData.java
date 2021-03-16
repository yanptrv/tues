package com.home.Kris;

public class PowerData {
    public final double minPower;
    public final double typicalPower;
    public final double maxPower;

    public PowerData(double minPower, double typicalPower, double maxPower) {
        this.minPower = minPower;
        this.typicalPower = typicalPower;
        this.maxPower = maxPower;
    }

    public double getMinPower() {
        return minPower;
    }

    public double getTypicalPower() {
        return typicalPower;
    }

    public double getMaxPower() {
        return maxPower;
    }
}
