package com.solvd.university.doc;

import com.solvd.university.people.Student;

public abstract class Certificate {

    private Student student;
    private int certScore;

    public Certificate(int certScore) {
        this.certScore = certScore;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getCertScore() {
        return certScore;
    }

    public void setCertScore(int certScore) {
        this.certScore = certScore;
    }
}
