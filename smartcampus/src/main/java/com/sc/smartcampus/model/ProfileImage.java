package com.sc.smartcampus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profile_images", indexes = {
        @Index(columnList = "entityType, entityId", unique = true)
})
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntityType entityType;  // Use enum instead of String

    @Column(nullable = false)
    private String entityId;   // e.g. roll number or faculty id

    private String fileName;

    private String contentType;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] data;

    // Constructors

    public ProfileImage() {}

    public ProfileImage(EntityType entityType, String entityId, String fileName, String contentType, byte[] data) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
