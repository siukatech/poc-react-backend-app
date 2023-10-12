package com.siukatech.poc.react.backend.app.business.service;

import com.siukatech.poc.react.backend.app.business.dto.InstantMsgDto;
import com.siukatech.poc.react.backend.app.business.dto.NotiDto;
import com.siukatech.poc.react.backend.app.data.entity.InstantMsgEntity;
import com.siukatech.poc.react.backend.app.data.entity.NotiEntity;
import com.siukatech.poc.react.backend.app.data.repository.InstantMsgRepository;
import com.siukatech.poc.react.backend.app.data.repository.NotiRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstantMsgService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ModelMapper modelMapper;
    private final InstantMsgRepository instantMsgRepository;

    public InstantMsgService(ModelMapper modelMapper, InstantMsgRepository instantMsgRepository) {
        this.modelMapper = modelMapper;
        this.instantMsgRepository = instantMsgRepository;
    }

    public Page<InstantMsgDto> findInstantMsgAllBySenderId(Long senderId, Pageable pageable) {
        Page<InstantMsgEntity> instantMsgEntityPage = this.instantMsgRepository.findAllByUserId(senderId, pageable);
        Page<InstantMsgDto> instantMsgDtoPage = instantMsgEntityPage.map(instantMsgEntity -> this.modelMapper.map(instantMsgEntity, InstantMsgDto.class));
        return instantMsgDtoPage;
    }

    public Page<InstantMsgDto> findInstantMsgAllByUserId(Long userId, Pageable pageable) {
        Page<InstantMsgEntity> instantMsgEntityPage = this.instantMsgRepository.findAllByUserId(userId, pageable);
        Page<InstantMsgDto> instantMsgDtoPage = instantMsgEntityPage.map(instantMsgEntity -> this.modelMapper.map(instantMsgEntity, InstantMsgDto.class));
        return instantMsgDtoPage;
    }

}
