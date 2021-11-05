package com.home.Kris;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Restaurant {
    private final Set<Dish> dishes = new HashSet<>();
    private final Set<Chef> chefs = new HashSet<>();
    private final Set<Waiter> waiters = new HashSet<>();
    private double money;
    private final Map<String, Order> activeOrders = new HashMap<>();
    private final Set<Order> completedOrders = new HashSet<>();
    private final Set<Order> failedOrders = new HashSet<>();

    public void addOrder(Order order) {
        this.activeOrders.put(order.getId(), order);
    }

    public void completeOrder(String id) throws Exception {
        if (activeOrders.containsKey(id)) {
            completedOrders.add(activeOrders.remove(id));
        } else {
            throw new Exception("No such order id");
        }
    }

    public void addChef(Chef chef) {
        this.chefs.add(chef);
    }

    public void addWaiter(Waiter waiter) {
        this.waiters.add(waiter);
    }
}
