package com.siukatech.poc.react.backend.app.figure.v1.data.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@EqualsAndHashCode(callSuper = true)
@Entity(name = "figure_shf")
public class FigureShfEntity extends FigureBaseEntity {
}
