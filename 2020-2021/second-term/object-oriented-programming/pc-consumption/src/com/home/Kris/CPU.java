package com.home.Kris;

public class CPU implements IPart {
    private final int cores;
    private final double speed;

    public CPU(int cores, double speed) {
        this.cores = cores;
        this.speed = speed;
    }

    @Override
    public PowerData getConsumption() {
        double typicalPower = cores*Math.pow(speed,2)/2*0.5;
        return new PowerData(typicalPower, typicalPower*0.25, typicalPower*5);
    }
}
