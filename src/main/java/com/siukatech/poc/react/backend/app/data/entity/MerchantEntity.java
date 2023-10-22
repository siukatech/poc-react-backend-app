package com.siukatech.poc.react.backend.app.data.entity;

import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "merchants")
public class MerchantEntity extends AbstractEntity {
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
