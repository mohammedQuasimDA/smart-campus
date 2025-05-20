package com.sc.smartcampus.dto;

import java.time.LocalDate;

public class StudentResponseDTO {

    private String rollNumber;
    private String name;
    private String email;
    private String department;          // e.g., "CS"
    private String departmentFullName;  // e.g., "Computer Science"
    private LocalDate enrollmentDate;

    public StudentResponseDTO() {
    }

    public StudentResponseDTO(String rollNumber, String name, String email,
                              String department, String departmentFullName,
                              LocalDate enrollmentDate) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.email = email;
        this.department = department;
        this.departmentFullName = departmentFullName;
        this.enrollmentDate = enrollmentDate;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
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

    public String getDepartmentFullName() {
        return departmentFullName;
    }

    public void setDepartmentFullName(String departmentFullName) {
        this.departmentFullName = departmentFullName;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
