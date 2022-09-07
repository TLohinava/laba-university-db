package com.solvd.university.people;

import com.solvd.university.doc.Certificate;
import com.solvd.university.doc.SchoolCert;
import com.solvd.university.doc.TestCertificate;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student extends Person {

    @XmlElementWrapper
    @XmlElements({
            @XmlElement(name = "testCertificate", type = TestCertificate.class),
            @XmlElement(name = "schoolCertificate", type = SchoolCert.class)
    })
    private List<Certificate> certificates;

    public Student() {
        super();
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
}