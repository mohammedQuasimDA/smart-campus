package com.sc.smartcampus.repository;

import com.sc.smartcampus.model.EntityType;
import com.sc.smartcampus.model.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    Optional<ProfileImage> findByEntityTypeAndEntityId(EntityType entityType, String entityId);

}
