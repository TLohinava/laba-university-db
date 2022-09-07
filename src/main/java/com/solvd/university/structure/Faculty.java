package com.solvd.university.structure;

import com.solvd.university.people.Student;
import com.solvd.university.people.staff.Employee;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "faculty")
@XmlType(propOrder = {"facultyName", "studentsCapacity", "employees", "students"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Faculty {

    private String facultyName;
    @XmlElementWrapper
    @XmlElement(name = "student")
    private List<Student> students;
    @XmlElementWrapper
    @XmlElement(name = "employee")
    private List<Employee> employees;
    private int studentsCapacity;

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

    public int getStudentsCapacity() {
        return studentsCapacity;
    }

    public void setStudentsCapacity(int studentsCapacity) {
        this.studentsCapacity = studentsCapacity;
    }
}