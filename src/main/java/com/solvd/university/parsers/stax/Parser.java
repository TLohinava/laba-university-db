package com.solvd.university.parsers.stax;

import com.solvd.university.doc.*;
import com.solvd.university.parsers.IParse;
import com.solvd.university.people.*;
import com.solvd.university.people.staff.Employee;
import com.solvd.university.structure.Faculty;
import com.solvd.university.structure.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Parser implements IParse {

    private List<Certificate> studentCerts;
    private List<Student> students;
    private List<Employee> employees;
    private University university;
    private Employee employee;
    private Student student;
    private TestCertificate testCert;
    private SchoolCert schoolCert;
    private Faculty faculty;
    private List<Faculty> faculties;
    private String name;
    private String surname;
    private String gender;
    private boolean hasUniname, hasDate, hasFacultyname, hasStudentcapacity, hasName, hasSurname, hasIssuedate, hasMark, hasSubject, hasMeanmark;

    private static final Logger LOGGER = LogManager.getLogger(Parser.class);

    @Override
    public University parse(String fileName) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(fileName));

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case "university":
                            university = new University();
                            faculties = new ArrayList<>();
                            university.setFaculties(faculties);
                            break;
                        case "uniName":
                            hasUniname = true;
                            break;
                        case "dateOfEstablishment":
                            hasDate = true;
                            break;
                        case "faculty":
                            faculty = new Faculty();
                            students = new ArrayList<>();
                            employees = new ArrayList<>();
                            faculties.add(faculty);
                            break;
                        case "facultyName":
                            hasFacultyname = true;
                            break;
                        case "studentsCapacity":
                            hasStudentcapacity = true;
                            break;
                        case "employee":
                            employee = new Employee();
                            Attribute genderAttribute = startElement.getAttributeByName(new QName("gender"));
                            if (genderAttribute != null) {
                                gender = genderAttribute.getValue().toUpperCase();
                            }
                            break;
                        case "student":
                            student = new Student();
                            break;
                        case "firstName":
                            hasName = true;
                            break;
                        case "surname":
                            hasSurname = true;
                            break;
                        case "certificates":
                            studentCerts = new ArrayList<>();
                            break;
                        case "testCertificate":
                            testCert = new TestCertificate();
                            studentCerts.add(testCert);
                            break;
                        case "issueDate":
                            hasIssuedate = true;
                            break;
                        case "mark":
                            hasMark = true;
                            break;
                        case "subject":
                            hasSubject = true;
                            break;
                        case "schoolCertificate":
                            schoolCert = new SchoolCert();
                            studentCerts.add(schoolCert);
                            break;
                        case "meanmark":
                            hasMeanmark = true;
                            break;
                    }
                } else if (event.isCharacters()) {
                    String data = event.asCharacters().getData();

                    if (hasUniname) {
                        university.setUniName(data);
                        hasUniname = false;
                    } else if (hasDate) {
                        university.setDateOfEstablishment(LocalDate.parse(data));
                        hasDate = false;
                    } else if (hasFacultyname) {
                        faculty.setFacultyName(data);
                        hasFacultyname = false;
                    } else if (hasStudentcapacity) {
                        faculty.setStudentsCapacity(Integer.parseInt(data));
                        hasStudentcapacity = false;
                    } else if (hasName) {
                        name = data;
                        hasName = false;
                    } else if (hasSurname) {
                        surname = data;
                        hasSurname = false;
                    } else if (hasIssuedate) {
                        testCert.setDateOfIssue(LocalDate.parse(data));
                        hasIssuedate = false;
                    } else if (hasMark) {
                        testCert.setCertScore(Integer.parseInt(data));
                        hasMark = false;
                    } else if (hasSubject) {
                        testCert.setSubject(data);
                        hasSubject = false;
                    } else if (hasMeanmark) {
                        schoolCert.setCertScore(Integer.parseInt(data));
                        hasMeanmark = false;
                    }
                } else if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();

                    switch (endElement.getName().getLocalPart()) {
                        case "faculty":
                            faculty.setStudents(students);
                            faculty.setEmployees(employees);
                            break;
                        case "employee":
                            employee.setFirstName(name);
                            employee.setSurname(surname);
                            employee.setGender(Person.Gender.valueOf(gender));
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
            LOGGER.error(e);
        }
        return university;
    }
}