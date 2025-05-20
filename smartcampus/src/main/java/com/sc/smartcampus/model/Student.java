package com.sc.smartcampus.model;

import java.time.LocalDate;

import com.sc.smartcampus.converter.DepartmentConverter;
import com.sc.smartcampus.validation.ValidRollNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students",indexes = @Index(columnList = "rollNumber",unique = true))
public class Student {

    @Id
    @Column(unique = true, length = 7) // Set to maximum possible length
    @ValidRollNumber
    private String rollNumber;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be 2-50 characters")
    private String name;

    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @Column(name = "department")
    @Convert(converter = DepartmentConverter.class)
    private Department department;

    @NotNull(message = "Enrollment date is required")
    private LocalDate enrollmentDate;

    public Student(String rollNumber, String name, String email, Department department, LocalDate enrollmentDate) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.email = email;
        this.department = department;
        this.enrollmentDate = enrollmentDate;
    }


    public Student() {

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}


