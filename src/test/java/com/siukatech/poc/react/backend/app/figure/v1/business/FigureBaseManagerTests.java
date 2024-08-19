package com.siukatech.poc.react.backend.app.figure.v1.business;

import com.siukatech.poc.react.backend.app.figure.v1.business.service.FigureBaseManager;
import com.siukatech.poc.react.backend.app.figure.v1.business.service.FigureBaseService;
import com.siukatech.poc.react.backend.app.figure.v1.business.service.FigureFigmaService;
import com.siukatech.poc.react.backend.app.figure.v1.business.service.FigureShfService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class FigureBaseManagerTests {

    @InjectMocks
    private FigureBaseManager figureBaseManager;
    @Mock
    private ApplicationContext applicationContext;
    @Mock
    private FigureShfService figureShfService;
    @Mock
    private FigureFigmaService figureFigmaService;

    private Map<String, FigureBaseService> prepareServiceMap_basic() {
        return Map.of("figureShfService", figureShfService, "figureFigmaService", figureFigmaService);
    }

    @Test
    public void resolveFigureBaseServiceByName_basic() {
        // given
        Map<String, FigureBaseService> serviceMap = prepareServiceMap_basic();
        when(applicationContext.getBeansOfType(FigureBaseService.class)).thenReturn(serviceMap);

        // when
        FigureBaseService figureBaseService = figureBaseManager.resolveFigureBaseServiceByName("shf");

        // then
        assertThat(figureBaseService).isNotNull();

    }

    @Test
    public void resolveFigureBaseServiceByName_not_found() {
        // given
        Map<String, FigureBaseService> serviceMap = prepareServiceMap_basic();
        when(applicationContext.getBeansOfType(FigureBaseService.class)).thenReturn(serviceMap);

        // when
        AtomicReference<FigureBaseService> serviceAtomicReference = new AtomicReference<>();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            serviceAtomicReference.set(figureBaseManager.resolveFigureBaseServiceByName("abcde"));
        });
        FigureBaseService figureBaseService = serviceAtomicReference.get();

        // then
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).contains("not found");
        assertThat(figureBaseService).isNull();

    }

}
