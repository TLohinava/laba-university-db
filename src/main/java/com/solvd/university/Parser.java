package com.solvd.university;

import com.solvd.university.doc.Certificate;
import com.solvd.university.doc.SchoolCert;
import com.solvd.university.doc.TestCertificate;
import com.solvd.university.people.Person;
import com.solvd.university.people.Student;
import com.solvd.university.people.staff.Employee;
import com.solvd.university.structure.University;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void parse(String fileName) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(fileName));

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    StartElement startElement = event.asStartElement();
                    String localName = startElement.getName().getLocalPart();

                    switch (localName) {
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
                            event = reader.nextEvent();
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
                        case "empname":
                            event = reader.nextEvent();
                            employee.setFirstName(event.asCharacters().getData());
                            break;
                        case "empsurname":
                            event = reader.nextEvent();
                            employee.setSurname(event.asCharacters().getData());
                            break;
                        case "student":
                            event = reader.nextEvent();
                            student = new Student("", "", Person.Gender.X);
                            break;
                        case "name":
                            event = reader.nextEvent();
                            student.setFirstName(event.asCharacters().getData());
                            break;
                        case "surname":
                            event = reader.nextEvent();
                            student.setSurname(event.asCharacters().getData());
                            break;
                        case "certificates":
                            event = reader.nextEvent();
                            studentCerts = new ArrayList<>();
                            break;
                        case "testcertificate":
                            event = reader.nextEvent();
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
                            event = reader.nextEvent();
                            schoolCert = new SchoolCert(0);
                            studentCerts.add(schoolCert);
                            break;
                        case "meanmark":
                            event = reader.nextEvent();
                            schoolCert.setCertScore(Integer.parseInt(event.asCharacters().getData()));
                            break;
                    }
                }

                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals("faculty")) {
                        faculty.setStudents(students);
                        faculty.setEmployees(employees);
                    } else if (endElement.getName().getLocalPart().equals("employee")) {
                        employees.add(employee);
                    } else if (endElement.getName().getLocalPart().equals("student")) {
                        student.setCertificates(studentCerts);
                        students.add(student);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}