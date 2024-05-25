package com.siukatech.poc.react.backend.app.figure.data.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "figure_shf")
public class FigureShfEntity extends FigureBaseEntity {
}
