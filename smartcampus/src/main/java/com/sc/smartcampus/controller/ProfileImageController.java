package com.sc.smartcampus.controller;

import com.sc.smartcampus.model.ProfileImage;
import com.sc.smartcampus.service.ProfileImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ProfileImageController {

    @Autowired
    private ProfileImageService imageService;

    @PostMapping("/{entityType}/{entityId}/upload")
    public ResponseEntity<String> uploadImage(@PathVariable String entityType,
                                              @PathVariable String entityId,
                                              @RequestParam("file") MultipartFile file) {
        try {
            imageService.storeProfileImage(entityType, entityId, file);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (RuntimeException e) {
            // Handles invalid entityType and other runtime errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Upload failed: " + e.getMessage());
        } catch (Exception e) {
            // Handles IOException etc
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/{entityType}/{entityId}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String entityType,
                                                      @PathVariable String entityId) {
        try {
            ProfileImage profileImage = imageService.getProfileImage(entityType, entityId);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(profileImage.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + profileImage.getFileName() + "\"")
                    .body(new ByteArrayResource(profileImage.getData()));
        } catch (RuntimeException e) {
            // Handles invalid entityType or image not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
