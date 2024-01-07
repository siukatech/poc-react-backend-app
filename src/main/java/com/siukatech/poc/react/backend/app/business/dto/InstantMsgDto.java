package com.siukatech.poc.react.backend.app.business.dto;

import com.siukatech.poc.react.backend.app.business.form.InstantMsgForm;
import com.siukatech.poc.react.backend.app.data.entity.InstantMsgEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class InstantMsgDto extends InstantMsgEntity {
}
