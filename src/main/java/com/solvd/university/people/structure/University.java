package com.solvd.university.people.structure;

import com.solvd.university.people.Student;
import com.solvd.university.people.staff.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class University {

    private String uniName;
    private LocalDate dateOfEstablishment;
    private List<Faculty> faculties;

    public University(String uniName, LocalDate dateOfEstablishment) {
        this.uniName = uniName;
        this.dateOfEstablishment = dateOfEstablishment;
    }

    public class Faculty {

        private String facultyName;
        private List<Student> students;
        private LocalDate dateOfEstablishment;
        private List<Employee> employees;
        private int studentsCapacity;

        public Faculty(String facultyName, int studentsCapacity) {
            this.facultyName = facultyName;
            this.studentsCapacity = studentsCapacity;
            this.students = new ArrayList<>(studentsCapacity);
        }

        public String getFacultyName() {
            return facultyName;
        }

        public void setFacultyName(String facultyName) {
            this.facultyName = facultyName;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }

        public LocalDate getDateOfEstablishment() {
            return dateOfEstablishment;
        }

        public void setDateOfEstablishment(LocalDate dateOfEstablishment) {
            this.dateOfEstablishment = dateOfEstablishment;
        }

        public int getStudentsCapacity() {
            return studentsCapacity;
        }

        public void setStudentsCapacity(int studentsCapacity) {
            this.studentsCapacity = studentsCapacity;
        }
    }

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