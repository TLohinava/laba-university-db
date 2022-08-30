package com.solvd.university.people;

public abstract class Person {

    private String firstName;
    private String surname;
    private Gender gender;

    public Person(String firstName, String surname, Gender gender) {
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
    }

    public enum Gender {
        MALE, FEMALE, X
    }

    public String getFullName() {
        return getFirstName() + " " + getSurname();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}