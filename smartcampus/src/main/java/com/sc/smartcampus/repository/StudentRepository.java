package com.sc.smartcampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sc.smartcampus.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("SELECT MAX(s.rollNumber) FROM Student s WHERE s.rollNumber LIKE :pattern")
    String findMaxRollNumberByPattern(@Param("pattern") String pattern);

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.rollNumber) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchByKeyword(@Param("keyword") String keyword);
}
