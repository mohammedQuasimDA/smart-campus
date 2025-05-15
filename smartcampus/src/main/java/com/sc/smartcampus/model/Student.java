package com.sc.smartcampus.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String department;
    private LocalDate enrollmentDate;

    public Student() {}

    public Student(Long id, String name, String email, String department, LocalDate enrollmentDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.enrollmentDate = enrollmentDate;
    }

    public Student(String name, String email, String department, LocalDate enrollmentDate) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.enrollmentDate = enrollmentDate;
    }

     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }


     @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }

}
