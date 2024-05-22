package com.siukatech.poc.react.backend.app.figure.business.service;

import com.siukatech.poc.react.backend.app.figure.data.entity.FigureBaseEntity;

public abstract class FigureBaseService<T extends FigureBaseEntity> {
    public abstract T prepareFigureBase(T figureBaseEntity);

}
