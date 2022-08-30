package com.solvd.university.people.staff;

public class Professor extends Employee {

    public Professor(String firstName, String surname, Gender gender) {
        super(firstName, surname, gender);
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