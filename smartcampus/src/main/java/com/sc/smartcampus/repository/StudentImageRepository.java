package com.sc.smartcampus.repository;

import com.sc.smartcampus.model.StudentImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentImageRepository extends MongoRepository<StudentImage, String> {
}
