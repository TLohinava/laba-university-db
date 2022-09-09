package com.solvd.university.structure;

import com.solvd.university.parsers.jaxb.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "university")
@XmlAccessorType(XmlAccessType.FIELD)
public class University {

    private String uniName;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate dateOfEstablishment;
    @XmlElementWrapper
    @XmlElement(name = "faculty")
    private List<Faculty> faculties;

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public LocalDate getDateOfEstablishment() {
        return dateOfEstablishment;
    }

    public void setDateOfEstablishment(LocalDate dateOfEstablishment) {
        this.dateOfEstablishment = dateOfEstablishment;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }
}