package com.siukatech.poc.react.backend.app.figure.v1.business.service;

import com.siukatech.poc.react.backend.app.figure.v1.data.entity.FigureBaseEntity;

public abstract class FigureBaseService<T extends FigureBaseEntity> {
    public abstract T prepareFigureBase(T figureBaseEntity);

}
