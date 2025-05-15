package com.sc.smartcampus.controller;

import com.sc.smartcampus.dto.StudentDto;
import com.sc.smartcampus.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

  
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //  Create new student
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto dto) {
        StudentDto created = studentService.createStudent(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    //  Get all students
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    //  Get student by ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    //  Update student
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto dto) {
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    //  Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
