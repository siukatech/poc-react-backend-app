package com.siukatech.poc.react.backend.app.base.data.entity;

import com.siukatech.poc.react.backend.core.data.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
//@EqualsAndHashCode(callSuper = true)
//@Entity(name = "instant_msgs")
@Entity
@Table(name = "instant_msgs")
public class InstantMsgEntity extends AbstractEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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
