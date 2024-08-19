package com.siukatech.poc.react.backend.app.figure.v2.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity(name = "figure_shf_v2")
//@PrimaryKeyJoinColumn(name = "sid")
//@PrimaryKeyJoinColumns(
//        value = {
//                @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id"),
//                @PrimaryKeyJoinColumn(name = "sid", referencedColumnName = "sid")
//        }
//)
public class FigureShfEntityV2 extends FigureBaseEntityV2 {
//
//    @CreatedBy
//    @Column(
//            name = "created_by"
//    )
//    private String createdBy;
//    @CreatedDate
//    @Column(
//            name = "created_datetime"
//    )
//    private LocalDateTime createdDatetime;
//    @LastModifiedBy
//    @Column(
//            name = "last_modified_by"
//    )
//    private String lastModifiedBy;
//    @LastModifiedDate
//    @Column(
//            name = "last_modified_datetime"
//    )
//    private LocalDateTime lastModifiedDatetime;
//    @Version
//    private Long versionNo;
}
