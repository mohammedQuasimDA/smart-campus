package com.sc.smartcampus.dto;

import java.time.LocalDate;

public class StudentDto {
    private String name;
    private String email;
    private String department;
    private LocalDate enrollmentDate;

    // --- Constructors ---

    public StudentDto() {
    }

    public StudentDto(String name, String email, String department, LocalDate enrollmentDate) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.enrollmentDate = enrollmentDate;
    }

    // --- Getters and Setters ---

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

    // --- toString() ---

    @Override
    public String toString() {
        return "StudentDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }
}
