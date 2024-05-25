package com.siukatech.poc.react.backend.app.figure.data.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "figure_figma")
public class FigureFigmaEntity extends FigureBaseEntity {
    private boolean hasPreorderBonus;
}
