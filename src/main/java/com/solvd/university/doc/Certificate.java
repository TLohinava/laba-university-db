package com.solvd.university.doc;

import com.solvd.university.people.Student;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "certificate")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Certificate {

    private Student student;
    private int certScore;

    public Certificate() {
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