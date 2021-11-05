package com.home.Kris;

public class Trainee {
    private final int id;
    private final String name;
    private int level = 1;
    private int numberOfTrainings = 0;


    public Trainee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfTrainings() {
        return numberOfTrainings;
    }

    public void addTraining() {
        this.numberOfTrainings++;
    }

    public void improveLevel() {
        this.level++;
    }
}
