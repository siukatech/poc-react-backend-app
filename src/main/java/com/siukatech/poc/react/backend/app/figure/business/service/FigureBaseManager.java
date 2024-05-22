package com.siukatech.poc.react.backend.app.figure.business.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class FigureBaseManager implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public FigureBaseService resolveFigureBaseServiceByName(String name) {
        Map<String, FigureBaseService> serviceMap = applicationContext.getBeansOfType(FigureBaseService.class);
        AtomicReference<FigureBaseService> serviceAtomicReference = new AtomicReference<>();
//        Optional<FigureBaseService> serviceOptional = null;
        serviceMap.forEach((serviceName, serviceBean) -> {
            if (serviceName.toLowerCase().contains(name.toLowerCase())) {
                serviceAtomicReference.set(serviceBean);
//                serviceOptional = Optional.of(serviceBean);
            }
        });
        FigureBaseService figureBaseService = serviceAtomicReference.get();
        if (figureBaseService == null) {
            throw new RuntimeException("Service not found");
        }
        return figureBaseService;
    }
}
