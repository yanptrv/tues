package com.home.Kris;

public class Employees {
    private final String firstName;
    private final String lastName;
    private final double salary;

    public Employees(String firstName, String lastName, double salary) throws Exception {
        if (salary < 0 || firstName.isBlank() || lastName.isBlank()) {
            throw new Exception("Salary must be >= 0 and you should fill the names");
        }
        else {
            this.firstName = firstName;
            this.lastName = lastName;
            this.salary = salary;
        }

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }
}


