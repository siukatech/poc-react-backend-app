package com.siukatech.poc.react.backend.app.figure.data.entity;

import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "figure_base")
@Inheritance(strategy = InheritanceType.JOINED)
public class FigureBaseEntity extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private LocalDate firstReleaseDate;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

}
