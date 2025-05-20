package com.sc.smartcampus.service.impl;

import com.sc.smartcampus.dto.StudentRequestDTO;
import com.sc.smartcampus.dto.StudentResponseDTO;
import com.sc.smartcampus.model.Department;
import com.sc.smartcampus.model.Student;
import com.sc.smartcampus.repository.StudentRepository;
import com.sc.smartcampus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sc.smartcampus.exception.DuplicateRollNumberException;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {


    private StudentRepository studentRepository;

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


// Implement all methods here...

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        // 1. Convert department code string to enum (throws exception if invalid)
        Department department = Department.fromCode(dto.getDepartment());

        // 2. Generate the year part from enrollment date
        String year = String.valueOf(dto.getEnrollmentDate().getYear()).substring(2); // last two digits, e.g. "23"

        // 3. Generate the next roll number for this year + department
        String nextRollNumber = generateNextRollNumber(year, department.getCode());

        // 4. Create new Student entity
        Student student = new Student();
        student.setRollNumber(nextRollNumber);
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setDepartment(department);
        student.setEnrollmentDate(dto.getEnrollmentDate());

        try {
            // 5. Save student entity
            student = studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            // 6. Throw a custom exception if rollNumber or email is duplicated
            throw new DuplicateRollNumberException("Student with roll number " + nextRollNumber + " or email already exists.");
        }

        // 6. Convert to response DTO and return
        return mapToResponseDTO(student);
    }



    @Override
    public StudentResponseDTO getStudentByRollNumber(String rollNumber) {
        Student student = studentRepository.findById(rollNumber)
                .orElseThrow(() -> new RuntimeException("Student not found with roll number: " + rollNumber));
        return mapToResponseDTO(student);
    }


    @Override
    public StudentResponseDTO updateStudent(String rollNumber, StudentRequestDTO dto) {
        // 1. Fetch the existing student by rollNumber
        Student student = studentRepository.findById(rollNumber)
                .orElseThrow(() -> new RuntimeException("Student not found with roll number: " + rollNumber));

        // 2. Update the fields from the DTO (except rollNumber, which is immutable)
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        // 3. Convert and update department (validate department code)
        Department department = Department.fromCode(dto.getDepartment());
        student.setDepartment(department);

        // 4. Update enrollment date
        student.setEnrollmentDate(dto.getEnrollmentDate());

        // 5. Save the updated student
        student = studentRepository.save(student);

        // 6. Convert to response DTO and return
        return mapToResponseDTO(student);
    }


    @Override
    public void deleteStudent(String rollNumber) {
        // Check if student exists, else throw exception
        Student student = studentRepository.findById(rollNumber)
                .orElseThrow(() -> new RuntimeException("Student not found with roll number: " + rollNumber));

        // Delete the student
        studentRepository.delete(student);
    }


    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<StudentResponseDTO> searchStudents(String keyword) {
        List<Student> students = studentRepository.searchByKeyword(keyword);
        return students.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public String generateNextRollNumber(String year, String departmentCode) {
        // 1. Create the pattern to find max existing roll numbers for the year+dept
        // Example pattern: "21CS%" to match roll numbers starting with 21CS
        String pattern = year + departmentCode + "%";

        // 2. Get the max existing roll number with this pattern from DB
        String maxRollNumber = studentRepository.findMaxRollNumberByPattern(pattern);

        int nextSerial = 1; // default if no existing roll numbers

        if (maxRollNumber != null) {
            // Extract the last 3 digits of the roll number (serial number)
            String serialStr = maxRollNumber.substring(maxRollNumber.length() - 3);
            try {
                int serialNum = Integer.parseInt(serialStr);
                nextSerial = serialNum + 1;
            } catch (NumberFormatException e) {
                // fallback if parsing fails, start from 1
                nextSerial = 1;
            }
        }

        // 3. Format the serial to be 3 digits with leading zeros (e.g., 001, 042)
        String serialFormatted = String.format("%03d", nextSerial);

        // 4. Build and return the next roll number string
        return year + departmentCode + serialFormatted;
    }


    // You can add private helper methods if needed, for example, a mapper from Student to DTO
}
