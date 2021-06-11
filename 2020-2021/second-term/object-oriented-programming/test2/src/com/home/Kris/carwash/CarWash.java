package com.home.Kris.carwash;

import java.util.ArrayList;

public class CarWash {
    private int orderId = 0;
    private final int id;
    private final int slots;
    private double cash;
    private final ArrayList<Order> activeOrders = new ArrayList<>();
    private final ArrayList<Order> completedOrders = new ArrayList<>();
    private final ArrayList<Service> services = new ArrayList<>();

    public CarWash(int id, int slots) {
        this.id = id;
        this.slots = slots;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void addOrder(Automobile automobile, ArrayList<Service> services) throws Exception {
        boolean status = false;
        for (Service i : services) {
            for (Service l : this.services)
            {
                if (i.getName().equals(l.getName())) {
                    status = true;
                    break;
                }
            }
            if (!status) {
                throw new Exception("Invalid service!");
            }
        }
        if (automobile == null) {
            throw new Exception("Invalid automobile!");
        }
        if (slots == 0) {
            throw new Exception("No more slots!");
        }
        else {
            orderId++;
            Order order = new Order(orderId, automobile, services);
            activeOrders.add(order);
        }

    }

    public void completeOrder(int id) throws Exception {
        boolean status = false;
        for (Order i : activeOrders) {
            if (i.getId() == id) {
                status = true;
                ArrayList<Service> services = i.getServices();
                for (Service s : services) {
                    cash += s.getPrice()*i.getAutomobile().getPriceMultiplayer();
                }
                break;
            }
        }
        if (!status) {
            throw new Exception("Invalid id!");
        }
    }

    public double getCash() {
        return cash;
    }

    public int getSlots() {
        return slots;
    }

    public int getId() {
        return id;
    }
}
