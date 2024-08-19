package com.siukatech.poc.react.backend.app.figure.v2.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumns;
import lombok.Data;

@Data
@Entity(name = "figure_figma_v2")
//@PrimaryKeyJoinColumns(
//        value = {
//                @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id"),
//                @PrimaryKeyJoinColumn(name = "sid", referencedColumnName = "sid")
//        }
//)
// JoinColumn is designed for field or method
//@JoinColumn(name = "sid", referencedColumnName = "sid")
public class FigureFigmaEntityV2 extends FigureBaseEntityV2 {
    private boolean hasPreorderBonus;
}
