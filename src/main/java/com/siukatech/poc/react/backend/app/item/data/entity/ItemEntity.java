package com.siukatech.poc.react.backend.app.item.data.entity;

import com.siukatech.poc.react.backend.core.data.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

//@Data 
////@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
//@ToString(callSuper = true)
@ToString
@Entity(name = "items")
//@AttributeOverride(name = "id", column = @Column(name = "id"))
public class ItemEntity extends AbstractEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;
    @Column
    protected String name;
    @Column(name = "purchased_date")
    protected LocalDate purchasedDate;
    @Column(name = "user_id")
    protected String userId;
}
