package com.home.Kris;

public class Main {
    public static void main(String[] args) {
        RegisterEmployee obj;
        Clients client = new Clients("Ivan", "Ivanov");

        {
            try {
                obj = new RegisterEmployee("Kris", "Petrov", 1000);
                obj.registerClient(client);
                System.out.println(client.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
