package com.siukatech.poc.react.backend.app.figure.v2.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "figure_base_v2")
@Table(name = "figure_base_v2")
@Inheritance(strategy = InheritanceType.JOINED)
//@PrimaryKeyJoinColumn(name = "sid")
public class FigureBaseEntityV2 extends FigureAbstractEntityV2 {
}
