package com.home.Kris;

import com.home.Kris.carwash.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Automobile automobile = new Automobile("BMW", "F32", "CAR");
            Service fullWash = new Service("FullWash", 60);
            ArrayList<Service> services = new ArrayList<Service>();
            services.add(fullWash);
            CarWash michellin = new CarWash(1, 10);
            michellin.addService(fullWash);
            michellin.addOrder(automobile, services);
            michellin.completeOrder(1);
            System.out.println(michellin.getCash());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
