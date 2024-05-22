package com.siukatech.poc.react.backend.app.figure.business.service;

import com.siukatech.poc.react.backend.app.figure.data.entity.FigureFigmaEntity;
import com.siukatech.poc.react.backend.app.figure.data.repository.FigureFigmaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FigureFigmaService extends FigureBaseService<FigureFigmaEntity> {

    private final FigureFigmaRepository figureFigmaRepository;

    public FigureFigmaService(FigureFigmaRepository figureFigmaRepository) {
        this.figureFigmaRepository = figureFigmaRepository;
    }

    @Override
    public FigureFigmaEntity prepareFigureBase(FigureFigmaEntity figureBaseEntity) {
        log.debug("prepareFigureBase - figureBaseEntity: [{}]", figureBaseEntity);
        return figureBaseEntity;
    }
}
