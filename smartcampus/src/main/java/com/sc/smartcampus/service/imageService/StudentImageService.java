package com.sc.smartcampus.service.imageService;

import com.sc.smartcampus.model.StudentImage;
import com.sc.smartcampus.repository.StudentImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StudentImageService {


    private StudentImageRepository studentImageRepository;

    public StudentImageService(StudentImageRepository studentImageRepository) {
        this.studentImageRepository = studentImageRepository;
    }

    public void saveImage(String rollNumber, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Uploaded file is empty");
        }

        StudentImage studentImage = new StudentImage(
                rollNumber,
                file.getBytes(),
                file.getContentType(),
                file.getOriginalFilename() // this sets the filename
        );

        studentImageRepository.save(studentImage);
    }

    public Optional<StudentImage> getImage(String rollNumber) {
        return studentImageRepository.findById(rollNumber);
    }
}
