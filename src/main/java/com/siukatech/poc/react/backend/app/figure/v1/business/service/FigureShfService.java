package com.siukatech.poc.react.backend.app.figure.v1.business.service;

import com.siukatech.poc.react.backend.app.figure.v1.data.entity.FigureShfEntity;
import com.siukatech.poc.react.backend.app.figure.v1.data.repository.FigureShfRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FigureShfService extends FigureBaseService<FigureShfEntity> {

    private final FigureShfRepository figureShfRepository;

    public FigureShfService(FigureShfRepository figureShfRepository) {
        this.figureShfRepository = figureShfRepository;
    }

    @Override
    public FigureShfEntity prepareFigureBase(FigureShfEntity figureBaseEntity) {
        log.debug("prepareFigureBase - figureBaseEntity: [{}]", figureBaseEntity);
        return figureBaseEntity;
    }
}
