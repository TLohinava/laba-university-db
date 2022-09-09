package com.solvd.university.people;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"firstName", "surname", "gender"})
public abstract class Person {

    @JsonProperty("name")
    private String firstName;
    private String surname;
    private Gender gender;

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