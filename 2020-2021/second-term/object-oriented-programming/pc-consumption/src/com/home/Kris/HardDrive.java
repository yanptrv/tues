package com.home.Kris;

public class HardDrive implements  IPart{
    double rpm;
    double capacity;


    @Override
    public PowerData getConsumption() {
        return new PowerData((rpm*capacity/160000),((rpm*capacity/160000)*0.1),((rpm*capacity/160000)*2));
    }
}
