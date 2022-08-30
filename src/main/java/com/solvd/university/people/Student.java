package com.solvd.university.people;

import com.solvd.university.doc.Certificate;

import java.util.List;

public class Student extends Person {

    private List<Certificate> certificates;

    public Student(String firstName, String surname, Gender gender) {
        super(firstName, surname, gender);
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
}