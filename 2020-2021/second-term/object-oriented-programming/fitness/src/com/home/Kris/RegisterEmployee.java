package com.home.Kris;

import java.util.ArrayList;
import java.util.List;

public class RegisterEmployee extends Employees{
    public RegisterEmployee(String firstName, String lastName, double salary) throws Exception {
        super(firstName, lastName, salary);
    }

    List<String> cardIDs = new ArrayList<String>();
    cardIDs.add("1203918764");
    cardIDs.add("1293087651");
    cardIDs.add("1203976853");
    cardIDs.add("1789203686");


    public void registerClient(Clients client) {
        int randomNumber = (int)(Math.random()*(cardIDs.size()+1)+0);
        client.setCard(new Card(cardIDs.get(randomNumber)));
        cardIDs.remove(randomNumber);

    }
}
