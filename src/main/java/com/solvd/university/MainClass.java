package com.solvd.university;

import com.solvd.university.doc.Certificate;
import com.solvd.university.doc.SchoolCert;
import com.solvd.university.doc.TestCertificate;
import com.solvd.university.people.*;
import com.solvd.university.people.staff.*;
import com.solvd.university.people.structure.University;

import java.time.LocalDate;
import java.util.*;

public class MainClass {

    public static void main(String[] args) {

        University bsmu = new University("Belarusian State Medical University", LocalDate.of(1921, 1, 1));

        University.Faculty generalMedicine = bsmu.new Faculty("General Medicine", 1000);
        University.Faculty pediatrics = bsmu.new Faculty("Pediatrics", 300);
        University.Faculty dentistry = bsmu.new Faculty("Dentistry", 50);
        University.Faculty pharmacology = bsmu.new Faculty("Pharmacology", 100);

        List<Employee> employeesGM = new ArrayList<>();
        employeesGM.add(new Professor("Andrey", "Andreevich", Person.Gender.MALE));
        employeesGM.add(new Professor("Anna", "Annova", Person.Gender.FEMALE));
        generalMedicine.setEmployees(employeesGM);

        List<Certificate> certificates = List.of(new TestCertificate(LocalDate.of(2022, 7, 1), (int) (Math.random() * 100 + 1), "Biology"),
                new TestCertificate(LocalDate.of(2022, 6, 25), (int) (Math.random() * 100 + 1), "Chemistry"),
                new TestCertificate(LocalDate.of(2022, 6, 17), (int) (Math.random() * 100 + 1), "Russian"),
                new SchoolCert((int) (Math.random() * 100 + 1)));

        List<Student> studentsGM = new ArrayList<>();
        studentsGM.add(new Student("Tuva", "Iner", Person.Gender.FEMALE));
        studentsGM.add(new Student("Alex", "May", Person.Gender.MALE));
        studentsGM.add(new Student("Ira", "Irovich", Person.Gender.FEMALE));
        generalMedicine.setStudents(studentsGM);
        studentsGM.forEach(student -> student.setCertificates(certificates));

        List<Employee> employeesPed = new ArrayList<>();
        employeesPed.add(new Professor("Alexey", "Andrush", Person.Gender.MALE));
        employeesPed.add(new Professor("Arina", "Antukh", Person.Gender.FEMALE));
        pediatrics.setEmployees(employeesPed);

        List<Student> studentsPed = new ArrayList<>();
        studentsPed.add(new Student("Nina", "Novik", Person.Gender.FEMALE));
        studentsPed.add(new Student("Alex", "May", Person.Gender.MALE));
        studentsPed.add(new Student("Klive", "Novak", Person.Gender.MALE));
        pediatrics.setStudents(studentsPed);
        studentsPed.forEach(student -> student.setCertificates(certificates));

        List<Employee> employeesDent = new ArrayList<>();
        employeesDent.add(new Professor("Alesia", "Andrush", Person.Gender.FEMALE));
        employeesDent.add(new Professor("Afina", "Atrushkova", Person.Gender.FEMALE));
        dentistry.setEmployees(employeesDent);

        List<Student> studentsDent = new ArrayList<>();
        studentsDent.add(new Student("Onja", "Itera", Person.Gender.FEMALE));
        studentsDent.add(new Student("Alex", "Udin", Person.Gender.MALE));
        studentsDent.add(new Student("Lera", "Naich", Person.Gender.FEMALE));
        dentistry.setStudents(studentsDent);
        studentsDent.forEach(student -> student.setCertificates(certificates));

        List<Employee> employeesPharm = new ArrayList<>();
        employeesPharm.add(new Professor("Ali", "Atoo", Person.Gender.MALE));
        employeesPharm.add(new Professor("Yanina", "Antonovna", Person.Gender.FEMALE));
        pharmacology.setEmployees(employeesPharm);

        List<Student> studentsPharm = new ArrayList<>();
        studentsPharm.add(new Student("Anita", "Iner", Person.Gender.FEMALE));
        studentsPharm.add(new Student("Kira", "Mayovich", Person.Gender.FEMALE));
        studentsPharm.add(new Student("Ira", "Nizkaya", Person.Gender.FEMALE));
        pharmacology.setStudents(studentsPharm);
        studentsPharm.forEach(student -> student.setCertificates(certificates));

        System.out.println(studentsDent.get(1).getCertificates().get(0).getCertScore());
        System.out.println(studentsDent.get(0).getCertificates().get(1).getCertScore());
    }
}