package com.solvd.university;

import com.solvd.university.doc.*;
import com.solvd.university.people.*;
import com.solvd.university.people.staff.Employee;
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
    String gender;
    boolean hasUniname, hasDate, hasFacultyname, hasStudentcapacity, hasName, hasSurname, hasIssuedate, hasMark, hasSubject, hasMeanmark;

    private static final Logger LOGGER = LogManager.getLogger(Parser.class);

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
                            hasUniname = true;
                            break;
                        case "date":
                            hasDate = true;
                            break;
                        case "faculty":
                            faculty = university.new Faculty("", 0);
                            students = new ArrayList<>();
                            employees = new ArrayList<>();
                            faculties.add(faculty);
                            break;
                        case "facultyname":
                            hasFacultyname = true;
                            break;
                        case "studentscapacity":
                            hasStudentcapacity = true;
                            break;
                        case "employee":
                            employee = new Employee("", "", Person.Gender.X);
                            Attribute genderAttribute = startElement.getAttributeByName(new QName("gender"));
                            if (genderAttribute != null) {
                                gender = genderAttribute.getValue().toUpperCase();
                            }
                            break;
                        case "student":
                            student = new Student("", "", Person.Gender.X);
                            break;
                        case "name":
                            hasName = true;
                            break;
                        case "surname":
                            hasSurname = true;
                            break;
                        case "certificates":
                            studentCerts = new ArrayList<>();
                            break;
                        case "testcertificate":
                            testCert = new TestCertificate(LocalDate.of(1970, 1, 1), 0, "");
                            studentCerts.add(testCert);
                            break;
                        case "issuedate":
                            hasIssuedate = true;
                            break;
                        case "mark":
                            hasMark = true;
                            break;
                        case "subject":
                            hasSubject = true;
                            break;
                        case "schoolcertificate":
                            schoolCert = new SchoolCert(0);
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
    }
}