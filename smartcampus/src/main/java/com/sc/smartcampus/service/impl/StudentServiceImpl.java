package com.sc.smartcampus.service.impl;

import com.sc.smartcampus.dto.StudentDto;
import com.sc.smartcampus.model.Student;
import com.sc.smartcampus.repository.StudentRepository;
import com.sc.smartcampus.service.StudentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    private StudentDto mapToDto(Student student) {
        return new StudentDto(
                student.getName(),
                student.getEmail(),
                student.getDepartment(),
                student.getEnrollmentDate()
        );
    }

    private Student mapToEntity(StudentDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setDepartment(dto.getDepartment());
        student.setEnrollmentDate(dto.getEnrollmentDate());
        return student;
    }

    @Override
    public StudentDto createStudent(StudentDto dto) {
        Student saved = repository.save(mapToEntity(dto));
        return mapToDto(saved);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return mapToDto(student);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setDepartment(dto.getDepartment());
        student.setEnrollmentDate(dto.getEnrollmentDate());
        return mapToDto(repository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}

