package com.siukatech.poc.react.backend.app.item.data.entity;

import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Data
@Entity(name = "attachments")
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

    /**
     * Reference:
     * https://stackoverflow.com/a/74630733
     * https://stackoverflow.com/a/75043614
     *
     * Both columnDefinition = "bytea" and @JdbcTypeCode(Types.BINARY) are required for Postgresql and h2 databases
     */
    @ToString.Exclude
    @Lob
    @JdbcTypeCode(Types.BINARY)
    @Column(name = "file_content", columnDefinition = "bytea")
    private byte[] fileContent;
    @Column(name = "user_id")
    protected String userId;

}

