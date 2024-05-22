package com.siukatech.poc.react.backend.app.item.data.entity;

import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//@Data
//@EqualsAndHashCode(callSuper=false)
@Setter
@Getter
@Entity(name = "items")
//@AttributeOverride(name = "id", column = @Column(name = "id"))
public class ItemEntity extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    protected String name;
    @Column(name = "purchased_date")
    protected LocalDate purchasedDate;
    @Column(name = "user_id")
    protected Long userId;
}
