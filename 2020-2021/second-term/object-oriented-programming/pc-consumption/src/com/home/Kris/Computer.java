package com.home.Kris;

import java.util.*;

public class Computer {
    private List<IPart> parts;


    public Computer(List<IPart> parts) {
        this.parts = parts;
    }

    public PowerData getConsumption() {
        PowerData sum = new PowerData(0, 0, 0);
    }
}
