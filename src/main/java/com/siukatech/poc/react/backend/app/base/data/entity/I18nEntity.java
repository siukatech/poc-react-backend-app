package com.siukatech.poc.react.backend.app.base.data.entity;

import com.siukatech.poc.react.backend.core.data.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@EqualsAndHashCode(callSuper = true)
@Entity(name = "i18n")
//@EntityListeners(AbstractEntityToPersistListener.class)
public class I18nEntity extends AbstractEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
//    @Column
//    private String messageKey;
    @Column
    private String messageEn;
    @Column
    private String messageTc;
    @Column
    private String messageSc;
}
