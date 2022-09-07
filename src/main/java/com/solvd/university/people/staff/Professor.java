package com.solvd.university.people.staff;

public class Professor extends Employee {

    public Professor() {
        super();
    }

    public String getTitle() {
        if ((this.getGender()).equals(Gender.MALE)) {
            return "Mr.";
        } else if ((this.getGender()).equals(Gender.FEMALE)) {
            return "Ms.";
        } else {
            return "Mx.";
        }
    }
}