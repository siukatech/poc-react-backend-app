package com.siukatech.poc.react.backend.app.base.data.entity;

import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name = "instant_msgs")
public class InstantMsgEntity extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String subject;
    @Column
    private String message;
    @Column
    private String status;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "sender_id")
    private Long senderId;

}
