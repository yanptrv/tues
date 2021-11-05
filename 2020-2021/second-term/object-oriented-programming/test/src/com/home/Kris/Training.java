package com.home.Kris;


import java.util.Arrays;

public class Training {
    int id = 0;
    String training;
    Trainee[] trainees;
    Trainer[] trainers;

    public Training(String training, Trainee[] trainees, Trainer[] trainers) {
        id++;
        this.training =training;
        this.trainees = Arrays.copyOf(trainees, trainees.length);
        this.trainers = Arrays.copyOf(trainers, trainers.length);
    }
}
