package com.siukatech.poc.react.backend.app.figure.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "figure_base")
//@Entity
@Table(name = "figure_base")
@Inheritance(strategy = InheritanceType.JOINED)
//public class FigureBaseEntity extends AbstractEntity<UUID> {
//public class FigureBaseEntity extends FigureCoreEntity {
public class FigureBaseEntity extends FigureAbstractEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//
//    private String name;
//    private LocalDate firstReleaseDate;
//
//    @Override
//    public UUID getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(UUID id) {
//        this.id = id;
//    }

}
