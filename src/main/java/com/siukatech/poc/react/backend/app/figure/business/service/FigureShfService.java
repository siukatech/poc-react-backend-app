package com.siukatech.poc.react.backend.app.figure.business.service;

import com.siukatech.poc.react.backend.app.figure.data.entity.FigureShfEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FigureShfService extends FigureBaseService<FigureShfEntity> {
    @Override
    public FigureShfEntity prepareFigureBase(FigureShfEntity figureBaseEntity) {
        log.debug("prepareFigureBase - figureBaseEntity: [{}]", figureBaseEntity);
        return figureBaseEntity;
    }
}
