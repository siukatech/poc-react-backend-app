package com.siukatech.poc.react.backend.app.base.data.entity;

import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name = "notis")
public class NotiEntity extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
