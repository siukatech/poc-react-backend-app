package com.siukatech.poc.react.backend.app.figure.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@Entity(name = "figure_core")
//@Entity
@Table(name = "figure_base")
//public class FigureCoreEntity extends AbstractEntity<UUID> {
public class FigureCoreEntity extends FigureAbstractEntity {

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
