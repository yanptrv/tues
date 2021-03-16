package com.home.Kris;

public class HardDrive implements IPart{
    private final int rpm;
    private final double capacity;

    public HardDrive(int rpm, double capacity) {
        this.rpm = rpm;
        this.capacity = capacity;
    }


    @Override
    public PowerData getConsumption() {
        return new PowerData((rpm*capacity/160000),((rpm*capacity/160000)*0.1),((rpm*capacity/160000)*2));
    }
}
