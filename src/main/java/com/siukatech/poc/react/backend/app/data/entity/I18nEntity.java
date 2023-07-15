package com.siukatech.poc.react.backend.app.data.entity;

import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import com.siukatech.poc.react.backend.parent.data.listener.AbstractEntityToPersistListener;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name = "i18n")
//@EntityListeners(AbstractEntityToPersistListener.class)
public class I18nEntity extends AbstractEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Column
    private String code;
    @Column
    private String messageEn;
    @Column
    private String messageZh;
    @Column
    private String messageCn;
}
