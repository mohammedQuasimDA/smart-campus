package com.sc.smartcampus.controller;

import com.sc.smartcampus.model.StudentImage;
import com.sc.smartcampus.service.imageService.StudentImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/students/upload")
public class StudentImageController {

    @Autowired
    private StudentImageService studentImageService;

    @PostMapping("/{rollNumber}/upload-image")
    public ResponseEntity<String> uploadImage(@PathVariable String rollNumber,
                                              @RequestParam("file") MultipartFile imageFile) throws IOException {
        studentImageService.saveImage(rollNumber, imageFile);
        return ResponseEntity.ok("Image uploaded successfully for roll number: " + rollNumber);
    }

    @GetMapping("/{rollNumber}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable String rollNumber) {
        Optional<StudentImage> imageOptional = studentImageService.getImage(rollNumber);

        if (imageOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        StudentImage image = imageOptional.get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getFileName() + "\"")
                .body(image.getImageData());
    }

}
