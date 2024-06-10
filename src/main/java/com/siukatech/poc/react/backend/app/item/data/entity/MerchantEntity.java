package com.siukatech.poc.react.backend.app.item.data.entity;

import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity(name = "merchants")
public class MerchantEntity extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mid;
    @Column
    private String name;
    @Column
    private String website;
    @Column
    private String description;
    @Column
    private String status;
}
