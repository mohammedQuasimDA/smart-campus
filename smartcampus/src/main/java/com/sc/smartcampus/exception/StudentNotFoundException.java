package com.sc.smartcampus.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String rollNumber) {
        super("Student not found with roll number: " + rollNumber);
    }
}
