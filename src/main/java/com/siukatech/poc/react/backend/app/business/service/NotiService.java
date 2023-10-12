package com.siukatech.poc.react.backend.app.business.service;

import com.siukatech.poc.react.backend.app.business.dto.NotiDto;
import com.siukatech.poc.react.backend.app.data.entity.NotiEntity;
import com.siukatech.poc.react.backend.app.data.repository.NotiRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotiService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
