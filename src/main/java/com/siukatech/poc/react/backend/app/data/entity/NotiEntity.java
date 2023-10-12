package com.siukatech.poc.react.backend.app.data.entity;

import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name = "notis")
public class NotiEntity extends AbstractEntity {

    @Column
    private String subject;
    @Column
    private String message;
    @Column
    private String status;
    @Column(name = "rel_type")
    private String relType;
    @Column(name = "rel_id")
    private Long relId;
    @Column(name = "user_id")
    private Long userId;

}
