package com.home.Kris;

import java.util.Arrays;

public class KarateClub {
    private Trainee[] trainees;
    private Trainer[] trainers;
    private Training[] trainings;

    public KarateClub(Trainee[] trainees, Trainer[] trainers, Training[] trainings) {
        this.trainees = Arrays.copyOf(trainees, trainees.length);
        this.trainers = Arrays.copyOf(trainers, trainers.length);
        this.trainings = Arrays.copyOf(trainings, trainings.length);
    }

    public boolean train(Trainer[] trainers, Trainee[] trainees, String training) throws ClubMemberNotFound {
        boolean flag = false;
        for (Trainer trainer : trainers) {
            for (Trainer insideTrainer : this.trainers) {
                if (trainer.getId() == insideTrainer.getId()) {
                    flag = true;
                    Training newTraining = new Training(training, trainees, trainers);
                    int n = this.trainings.length;
                    this.trainings = new Training[n+1];
                    this.trainings[n+1] = newTraining;
                }
                else {
                    throw new ClubMemberNotFound("No such member in this club:" + trainer.getName());
                }
            }
        }

        for (Trainee trainee : trainees) {
            for (Trainee insideTrainee : this.trainees) {
                if (trainee.getId() == insideTrainee.getId()) {
                    trainee.addTraining();
                    if(trainee.getNumberOfTrainings() % 10 == 0) {
                        trainee.improveLevel();
                    }
                    flag = true;
                }
                else {
                    throw new ClubMemberNotFound("No such member in this club:" + trainee.getName());
                }
            }
        }
        return flag;
    }

    public void addTrainer(Trainer trainer) throws ExistingTrainer {
        for (Trainer train : this.trainers) {
            if (trainer.getId() == train.getId()) {
                throw new ExistingTrainer("There already is such trainer");
            }
            else {
                int n = this.trainers.length;
                this.trainers = new Trainer[n+1];
                this.trainers[n+1] = trainer;
            }
        }
    }

    public void addTrainee(Trainee trainee) throws ExistingTrainee{
        for (Trainee train : this.trainees) {
            if (trainee.getId() == train.getId()) {
                throw new ExistingTrainee("There already is such trainee");
            }
            else {
                int n = this.trainees.length;
                this.trainees = new Trainee[n+1];
                this.trainees[n+1] = trainee;
            }
        }
    }
}
