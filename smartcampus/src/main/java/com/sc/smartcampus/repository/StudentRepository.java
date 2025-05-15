package com.sc.smartcampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sc.smartcampus.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
    
}
