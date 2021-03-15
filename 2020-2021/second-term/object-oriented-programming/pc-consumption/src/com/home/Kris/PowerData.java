package com.home.Kris;

public class PowerData {
    final double minPower;
    final double typicalPower;
    final double maxPower;

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
