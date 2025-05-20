package com.sc.smartcampus.service;

import com.sc.smartcampus.model.EntityType;
import com.sc.smartcampus.model.ProfileImage;
import com.sc.smartcampus.repository.ProfileImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfileImageService {

    @Autowired
    private ProfileImageRepository repository;

    public ProfileImage storeProfileImage(String entityTypeStr, String entityId, MultipartFile file) throws IOException {
        EntityType entityType = parseEntityType(entityTypeStr);

        ProfileImage profileImage = repository.findByEntityTypeAndEntityId(entityType, entityId)
                .orElse(new ProfileImage());

        profileImage.setEntityType(entityType);
        profileImage.setEntityId(entityId);
        profileImage.setFileName(file.getOriginalFilename());
        profileImage.setContentType(file.getContentType());
        profileImage.setData(file.getBytes());

        return repository.save(profileImage);
    }

    public ProfileImage getProfileImage(String entityTypeStr, String entityId) {
        EntityType entityType = parseEntityType(entityTypeStr);

        return repository.findByEntityTypeAndEntityId(entityType, entityId)
                .orElseThrow(() -> new RuntimeException("Image not found for entityType: " + entityType + ", entityId: " + entityId));
    }

    private EntityType parseEntityType(String entityTypeStr) {
        try {
            return EntityType.valueOf(entityTypeStr.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Invalid entityType: " + entityTypeStr + ". Allowed values: STUDENT, FACULTY");
        }
    }
}
