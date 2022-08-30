package com.solvd.university.doc;

import java.time.*;

public class TestCertificate extends Certificate {

    private LocalDate dateOfIssue;
    private String subject;

    public TestCertificate(LocalDate dateOfIssue, int certScore, String subject) {
        super(certScore);
        this.dateOfIssue = dateOfIssue;
        this.subject = subject;
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