package com.solvd.university.doc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "schoolCertificate")
@XmlAccessorType(XmlAccessType.FIELD)
public class SchoolCert extends Certificate {

    public SchoolCert() {
        super();
    }
}