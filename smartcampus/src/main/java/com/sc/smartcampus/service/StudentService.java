package com.sc.smartcampus.service;

import com.sc.smartcampus.dto.StudentRequestDTO;
import com.sc.smartcampus.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {

    StudentResponseDTO createStudent(StudentRequestDTO dto);

    StudentResponseDTO getStudentByRollNumber(String rollNumber);

    StudentResponseDTO updateStudent(String rollNumber, StudentRequestDTO dto);

    void deleteStudent(String rollNumber);

    List<StudentResponseDTO> getAllStudents();

    List<StudentResponseDTO> searchStudents(String keyword);

    String generateNextRollNumber(String year, String departmentCode);
}