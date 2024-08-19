package com.siukatech.poc.react.backend.app.figure.v2.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "figure_core_v2")
@Table(name = "figure_base_v2")
@PrimaryKeyJoinColumn(name = "sid")
//@JoinColumn(name = "sid", referencedColumnName = "sid")
public class FigureCoreEntityV2 extends FigureAbstractEntityV2 {
}
