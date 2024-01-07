package com.siukatech.poc.react.backend.app.data.entity;

import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name = "attachment")
public class AttachmentEntity extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "file_name")
    private String fileName;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "file_size")
    private int fileSize;

    @Lob
    @Column(name = "file_content")
    private byte[] fileContent;
    @Column(name = "user_id")
    protected Long userId;

}

