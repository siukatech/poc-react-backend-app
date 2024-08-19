package com.siukatech.poc.react.backend.app.figure.v2.data.entity;

import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class FigureAbstractEntityV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @Id
    private String sid;

    private String name;
    private LocalDate firstReleaseDate;

//    @Override
    public UUID getId() {
        return id;
    }

//    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @CreatedBy
    @Column(
            name = "created_by"
    )
    private String createdBy;
    @CreatedDate
    @Column(
            name = "created_datetime"
    )
    private LocalDateTime createdDatetime;
    @LastModifiedBy
    @Column(
            name = "last_modified_by"
    )
    private String lastModifiedBy;
    @LastModifiedDate
    @Column(
            name = "last_modified_datetime"
    )
    private LocalDateTime lastModifiedDatetime;
    @Version
    private Long versionNo;


}
