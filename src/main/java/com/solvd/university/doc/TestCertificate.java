package com.solvd.university.doc;

import com.solvd.university.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.*;

@XmlRootElement(name = "testCertificate")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestCertificate extends Certificate {
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate dateOfIssue;
    @XmlElement
    private String subject;

    public TestCertificate() {
        super();
    }

    public static int calcCertScore() {
        return Math.toIntExact(Math.round(Math.random() * 100));
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}