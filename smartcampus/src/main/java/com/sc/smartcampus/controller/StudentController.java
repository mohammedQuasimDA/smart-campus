package com.sc.smartcampus.controller;

import com.sc.smartcampus.dto.StudentRequestDTO;
import com.sc.smartcampus.dto.StudentResponseDTO;
import com.sc.smartcampus.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 1. Create Student
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO dto) {
        StudentResponseDTO created = studentService.createStudent(dto);
        return ResponseEntity.ok(created);
    }

    // 2. Get Student by Roll Number
    @GetMapping("/{rollNumber}")
    public ResponseEntity<StudentResponseDTO> getStudentByRollNumber(@PathVariable String rollNumber) {
        StudentResponseDTO student = studentService.getStudentByRollNumber(rollNumber);
        return ResponseEntity.ok(student);
    }

    // 3. Update Student by Roll Number
    @PutMapping("/{rollNumber}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable String rollNumber,
                                                            @Valid @RequestBody StudentRequestDTO dto) {
        StudentResponseDTO updated = studentService.updateStudent(rollNumber, dto);
        return ResponseEntity.ok(updated);
    }

    // 4. Delete Student by Roll Number
    @DeleteMapping("/{rollNumber}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String rollNumber) {
        studentService.deleteStudent(rollNumber);
        return ResponseEntity.noContent().build();
    }

    // 5. Get All Students
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        List<StudentResponseDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // 6. Search Students by keyword
    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDTO>> searchStudents(@RequestParam String keyword) {
        List<StudentResponseDTO> results = studentService.searchStudents(keyword);
        return ResponseEntity.ok(results);
    }
}
