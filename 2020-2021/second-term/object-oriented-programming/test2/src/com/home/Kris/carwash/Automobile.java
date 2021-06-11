package com.home.Kris.carwash;

public class Automobile {
    private final String maker;
    private final String model;
    private final double priceMultiplayer;
    private final String type;

    public Automobile(String maker, String model, String type) throws Exception {
        if (maker.isEmpty() || maker.isBlank()) {
            throw new Exception("Invalid maker!");
        }
        else {
            this.maker = maker;
        }
        if (model.isEmpty() || maker.isBlank()) {
            throw new Exception("Invalid model!");
        }
        else {
            this.model = model;
        }
        switch (type) {
            case "CAR" -> {
                this.priceMultiplayer = 1;
                this.type = type;
            }
            case "TRUCK" -> {
                this.priceMultiplayer = 2;
                this.type = type;
            }
            case "SUV" -> {
                this.priceMultiplayer = 1.5;
                this.type = type;
            }
            default -> throw new Exception("Invalid type!");
        }
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public double getPriceMultiplayer() {
        return priceMultiplayer;
    }

    public String getType() {
        return type;
    }
}
