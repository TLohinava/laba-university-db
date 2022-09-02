package com.solvd.university;

import com.solvd.university.doc.*;
import com.solvd.university.people.*;
import com.solvd.university.people.staff.Employee;
import com.solvd.university.structure.University;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Parser implements IParse {

    List<Certificate> studentCerts;
    List<Student> students;
    List<Employee> employees;
    University university;
    Employee employee;
    Student student;
    TestCertificate testCert;
    SchoolCert schoolCert;
    University.Faculty faculty;
    List<University.Faculty> faculties;
    String name;
    String surname;

    @Override
    public void parse(String fileName) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(fileName));

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case "university":
                            university = new University("", LocalDate.of(1970, 1, 1));
                            faculties = new ArrayList<>();
                            university.setFaculties(faculties);
                            break;
                        case "uniname":
                            event = reader.nextEvent();
                            university.setUniName(event.asCharacters().getData());
                            break;
                        case "date":
                            event = reader.nextEvent();
                            university.setDateOfEstablishment(LocalDate.parse(event.asCharacters().getData()));
                            break;
                        case "faculty":
                            faculty = university.new Faculty("", 0);
                            students = new ArrayList<>();
                            employees = new ArrayList<>();
                            faculties.add(faculty);
                            break;
                        case "facultyname":
                            event = reader.nextEvent();
                            faculty.setFacultyName(event.asCharacters().getData());
                            break;
                        case "studentscapacity":
                            event = reader.nextEvent();
                            faculty.setStudentsCapacity(Integer.parseInt(event.asCharacters().getData()));
                            break;
                        case "employee":
                            employee = new Employee("", "", Person.Gender.X);
                            Attribute genderAttribute = startElement.getAttributeByName(new QName("gender"));
                            if (genderAttribute != null) {
                                String gen = genderAttribute.getValue().toUpperCase();
                                employee.setGender(Person.Gender.valueOf(gen));
                            }
                            break;
                        case "student":
                            student = new Student("", "", Person.Gender.X);
                            break;
                        case "name":
                            event = reader.nextEvent();
                            name = event.asCharacters().getData();
                            break;
                        case "surname":
                            event = reader.nextEvent();
                            surname = event.asCharacters().getData();
                            break;
                        case "certificates":
                            studentCerts = new ArrayList<>();
                            break;
                        case "testcertificate":
                            testCert = new TestCertificate(LocalDate.of(1970, 1, 1), 0, "");
                            studentCerts.add(testCert);
                            break;
                        case "issuedate":
                            event = reader.nextEvent();
                            testCert.setDateOfIssue(LocalDate.parse(event.asCharacters().getData()));
                            break;
                        case "mark":
                            event = reader.nextEvent();
                            testCert.setCertScore(Integer.parseInt(event.asCharacters().getData()));
                            break;
                        case "subject":
                            event = reader.nextEvent();
                            testCert.setSubject(event.asCharacters().getData());
                            break;
                        case "schoolcertificate":
                            schoolCert = new SchoolCert(0);
                            studentCerts.add(schoolCert);
                            break;
                        case "meanmark":
                            event = reader.nextEvent();
                            schoolCert.setCertScore(Integer.parseInt(event.asCharacters().getData()));
                            break;
                    }
                } else if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();

                    switch(endElement.getName().getLocalPart()) {
                        case "faculty":
                            faculty.setStudents(students);
                            faculty.setEmployees(employees);
                            break;
                        case "employee":
                            employee.setFirstName(name);
                            employee.setSurname(surname);
                            employees.add(employee);
                            break;
                        case "student":
                            student.setFirstName(name);
                            student.setSurname(surname);
                            student.setCertificates(studentCerts);
                            students.add(student);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        System.out.println(university.getUniName());
        System.out.println(university.getFaculties().get(3).getStudents().size());
        System.out.println(university.getFaculties().get(3).getStudents().get(2).getFullName());
    }
}