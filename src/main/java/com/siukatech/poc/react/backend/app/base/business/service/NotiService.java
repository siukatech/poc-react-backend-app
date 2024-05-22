package com.siukatech.poc.react.backend.app.base.business.service;

import com.siukatech.poc.react.backend.app.base.business.dto.NotiDto;
import com.siukatech.poc.react.backend.app.base.data.entity.NotiEntity;
import com.siukatech.poc.react.backend.app.base.data.repository.NotiRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotiService {

    private final ModelMapper modelMapper;
    private final NotiRepository notiRepository;

    public NotiService(ModelMapper modelMapper, NotiRepository notiRepository) {
        this.modelMapper = modelMapper;
        this.notiRepository = notiRepository;
    }

    public Page<NotiDto> findNotiAllByUserId(Long userId, Pageable pageable) {
        Page<NotiEntity> notiEntityPage = this.notiRepository.findAllByUserIdAndStatus(userId, "UNREAD", pageable);
        Page<NotiDto> notiDtoPage = notiEntityPage.map(notiEntity -> this.modelMapper.map(notiEntity, NotiDto.class));
        return notiDtoPage;
    }

}
