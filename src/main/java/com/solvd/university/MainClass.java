package com.solvd.university;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.university.doc.SchoolCert;
import com.solvd.university.doc.TestCertificate;
import com.solvd.university.people.Person;
import com.solvd.university.people.Student;
import com.solvd.university.people.staff.Employee;
import com.solvd.university.structure.Faculty;
import com.solvd.university.structure.University;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class MainClass {

    private static final Logger LOGGER = LogManager.getLogger(MainClass.class);

    public static void main(String[] args) {
            String fileName = "./src/main/resources/uni.xml";
            IParse demo = new Parser();
            demo.parse(fileName);

            String fileName1 = "./src/main/resources/uniJaxb.xml";
            IParse jaxb = new JaxbParser();
            jaxb.parse(fileName1);

            University bsmu = new University();
            bsmu.setUniName("BSMU");
            bsmu.setDateOfEstablishment(LocalDate.of(1970, 1, 1));

            Student alice = new Student();
            alice.setFirstName("Alisa");
            alice.setSurname("Shunkevich");
            alice.setGender(Person.Gender.FEMALE);
            Employee mark = new Employee();
            mark.setFirstName("Mark");
            mark.setSurname("Avrelii");
            mark.setGender(Person.Gender.MALE);

            TestCertificate bio = new TestCertificate();
            bio.setCertScore(80);
            bio.setSubject("Biology");
            bio.setDateOfIssue(LocalDate.of(2022, 7, 1));
            TestCertificate chem = new TestCertificate();
            chem.setCertScore(84);
            chem.setSubject("Chemistry");
            chem.setDateOfIssue(LocalDate.of(2022, 6, 22));
            TestCertificate rus = new TestCertificate();
            rus.setCertScore(86);
            rus.setSubject("Russian");
            rus.setDateOfIssue(LocalDate.of(2022, 6, 15));
            SchoolCert school = new SchoolCert();
            school.setCertScore(85);

            alice.setCertificates(List.of(bio, chem, rus, school));

            Faculty genMed = new Faculty();
            genMed.setFacultyName("General Medicine");
            genMed.setEmployees(List.of(mark, mark));
            genMed.setStudentsCapacity(1000);
            genMed.setStudents(List.of(alice));

            Faculty ped = new Faculty();
            ped.setFacultyName("Pediatrics");
            ped.setEmployees(List.of(mark));
            ped.setStudentsCapacity(300);
            ped.setStudents(List.of(alice));

            Faculty pharm = new Faculty();
            pharm.setFacultyName("Pharmacology");
            pharm.setEmployees(List.of(mark));
            pharm.setStudentsCapacity(100);
            pharm.setStudents(List.of(alice));

            Faculty dent = new Faculty();
            dent.setFacultyName("Dentistry");
            dent.setEmployees(List.of(mark));
            dent.setStudentsCapacity(50);
            dent.setStudents(List.of(alice));

            bsmu.setFaculties(List.of(genMed, ped, pharm, dent));

            try {
                    JAXBContext context = JAXBContext.newInstance(University.class, Faculty.class, Student.class, SchoolCert.class, TestCertificate.class);
                    Marshaller marshaller = context.createMarshaller();
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    marshaller.marshal(bsmu, new File("./src/main/resources/uniJaxb.xml"));
            } catch (JAXBException e) {
                LOGGER.error(e);
            }

            ObjectMapper mapper = new ObjectMapper();
    }
}