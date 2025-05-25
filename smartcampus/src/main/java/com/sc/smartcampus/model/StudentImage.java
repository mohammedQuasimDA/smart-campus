package com.sc.smartcampus.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student_images")
public class StudentImage {

    @Id
    private String studentRollNumber;

    private byte[] imageData;
    private String contentType;
    private String fileName; // <-- Add this line

    public StudentImage() {}

    public StudentImage(String studentRollNumber, byte[] imageData, String contentType, String fileName) {
        this.studentRollNumber = studentRollNumber;
        this.imageData = imageData;
        this.contentType = contentType;
        this.fileName = fileName;
    }

    public String getStudentRollNumber() {
        return studentRollNumber;
    }

    public void setStudentRollNumber(String studentRollNumber) {
        this.studentRollNumber = studentRollNumber;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() { // <-- Add this method
        return fileName;
    }

    public void setFileName(String fileName) { // <-- And this method
        this.fileName = fileName;
    }
}
