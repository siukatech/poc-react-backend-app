package com.siukatech.poc.react.backend.app.base.business.service;

import com.siukatech.poc.react.backend.app.base.business.dto.InstantMsgDto;
import com.siukatech.poc.react.backend.app.base.data.entity.InstantMsgEntity;
import com.siukatech.poc.react.backend.app.base.data.repository.InstantMsgRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InstantMsgService {

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
