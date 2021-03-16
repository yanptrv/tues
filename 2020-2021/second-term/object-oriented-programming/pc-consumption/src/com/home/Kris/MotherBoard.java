package com.home.Kris;

public class MotherBoard implements IPart{
    private final String chipSet;

    public MotherBoard(String chipSet) {
        this.chipSet = chipSet;
    }

    @Override
    public PowerData getConsumption() {
        double typicalPower = switch (chipSet) {
            case "am1" -> 25;
            case "am2" -> 35;
            case "am3" -> 50;
            default -> 30;
        };
        return new PowerData(typicalPower, typicalPower, typicalPower);
    }
}
