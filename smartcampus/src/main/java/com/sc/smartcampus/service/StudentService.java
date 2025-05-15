package com.sc.smartcampus.service;

import com.sc.smartcampus.dto.StudentDto;
import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto dto);
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    StudentDto updateStudent(Long id, StudentDto dto);
    void deleteStudent(Long id);
}
