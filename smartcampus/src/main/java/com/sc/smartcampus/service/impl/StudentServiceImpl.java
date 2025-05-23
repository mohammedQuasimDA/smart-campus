package com.sc.smartcampus.service.impl;

import com.sc.smartcampus.dto.StudentRequestDTO;
import com.sc.smartcampus.dto.StudentResponseDTO;
import com.sc.smartcampus.exception.DuplicateRollNumberException;
import com.sc.smartcampus.exception.InvalidDepartmentCodeException;
import com.sc.smartcampus.exception.StudentNotFoundException;
import com.sc.smartcampus.model.Department;
import com.sc.smartcampus.model.Student;
import com.sc.smartcampus.repository.StudentRepository;
import com.sc.smartcampus.service.StudentService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private StudentResponseDTO mapToResponseDTO(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setRollNumber(student.getRollNumber());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setDepartment(student.getDepartment().getCode());
        dto.setDepartmentFullName(student.getDepartment().getFullName());
        dto.setEnrollmentDate(student.getEnrollmentDate());
        return dto;
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        Department department;
        try {
            department = Department.fromCode(dto.getDepartment());
        } catch (IllegalArgumentException e) {
            throw new InvalidDepartmentCodeException("Invalid department code: " + dto.getDepartment());
        }

        String year = String.valueOf(dto.getEnrollmentDate().getYear()).substring(2); // last 2 digits
        String nextRollNumber = generateNextRollNumber(year, department.getCode());

        Student student = new Student();
        student.setRollNumber(nextRollNumber);
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setDepartment(department);
        student.setEnrollmentDate(dto.getEnrollmentDate());

        try {
            student = studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateRollNumberException("Student with roll number " + nextRollNumber + " or email already exists.");
        }

        return mapToResponseDTO(student);
    }

    @Override
    public StudentResponseDTO getStudentByRollNumber(String rollNumber) {
        Student student = studentRepository.findById(rollNumber)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with roll number: " + rollNumber));
        return mapToResponseDTO(student);
    }

    @Override
    public StudentResponseDTO updateStudent(String rollNumber, StudentRequestDTO dto) {
        Student student = studentRepository.findById(rollNumber)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with roll number: " + rollNumber));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        Department department;
        try {
            department = Department.fromCode(dto.getDepartment());
        } catch (IllegalArgumentException e) {
            throw new InvalidDepartmentCodeException("Invalid department code: " + dto.getDepartment());
        }

        student.setDepartment(department);
        student.setEnrollmentDate(dto.getEnrollmentDate());

        student = studentRepository.save(student);

        return mapToResponseDTO(student);
    }

    @Override
    public void deleteStudent(String rollNumber) {
        Student student = studentRepository.findById(rollNumber)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with roll number: " + rollNumber));

        studentRepository.delete(student);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> searchStudents(String keyword) {
        return studentRepository.searchByKeyword(keyword).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String generateNextRollNumber(String year, String departmentCode) {
        String pattern = year + departmentCode + "%";
        String maxRollNumber = studentRepository.findMaxRollNumberByPattern(pattern);

        int nextSerial = 1;

        if (maxRollNumber != null) {
            String serialStr = maxRollNumber.substring(maxRollNumber.length() - 3);
            try {
                nextSerial = Integer.parseInt(serialStr) + 1;
            } catch (NumberFormatException ignored) {
                nextSerial = 1;
            }
        }

        String serialFormatted = String.format("%03d", nextSerial);
        return year + departmentCode + serialFormatted;
    }
}
