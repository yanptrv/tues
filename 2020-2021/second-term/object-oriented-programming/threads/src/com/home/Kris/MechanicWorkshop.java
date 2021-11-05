package com.home.Kris;

public class MechanicWorkshop implements Runnable {
    private int carCount = 0;
    private int carMax = 10;

    public void addCar () {
        carCount = carCount + 1;
    }
    @Override
    public void run() {
        try {
            while(carCount < carMax) {
                System.out.println("Not enough cars");
                System.out.println("Current cars: " + carCount);
                Thread.sleep(1000);
            }
            System.out.println("Car entering the garage");
            System.out.println("Fixing the car...");
            Thread.sleep(1000);
            System.out.println("Car leaving the garage");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
