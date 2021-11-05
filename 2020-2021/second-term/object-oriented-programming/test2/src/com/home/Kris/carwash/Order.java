package com.home.Kris.carwash;

import java.util.ArrayList;


public class Order {
    private final int id;
    private final Automobile automobile;
    private final ArrayList<Service> services;

    public Order(int id, Automobile obj, ArrayList<Service> services) throws Exception {
        if (id <= 0) {
            throw new Exception("Invalid id!");
        }
        else {
            this.id = id;
        }
        if (obj == null) {
            throw new Exception("Invalid automobile!");
        }
        else {
            this.automobile = obj;
        }
        if (services == null) {
            throw new Exception("Invalid services!");
        }
        else {
            this.services = services;
        }
    }

    public int getId() {
        return id;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public ArrayList<Service> getServices() {
        return services;
    }
}
