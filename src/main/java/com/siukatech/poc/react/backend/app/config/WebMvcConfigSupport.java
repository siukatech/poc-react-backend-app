package com.siukatech.poc.react.backend.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.format.DateTimeFormatter;

/**
 *
 * this is not required.
 *
 * for customization, pls refer to
 * https://medium.com/@andylke/rest-controller-configuration-date-time-format-in-json-response-201e97aa74b0
 */

@Deprecated
//@Configuration
public class WebMvcConfigSupport extends WebMvcConfigurationSupport {

    public final static String DEFAULT_FORMAT_PATTERN_DATE = "yyyy-MM-dd";
    public final static String DEFAULT_FORMAT_PATTERN_TIME = "HH:mm:ss";
    public final static String DEFAULT_FORMAT_PATTERN_DATETIME = DEFAULT_FORMAT_PATTERN_DATE + " " + DEFAULT_FORMAT_PATTERN_TIME;

//    @Bean
    @Override
    public FormattingConversionService mvcConversionService() {
//        FormattingConversionService conversionService = new DefaultFormattingConversionService();
//        addFormatters(conversionService);
        FormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

        DateTimeFormatterRegistrar dateTimeFormatterRegistrar = new DateTimeFormatterRegistrar();
        dateTimeFormatterRegistrar.setDateFormatter(DateTimeFormatter.ofPattern(DEFAULT_FORMAT_PATTERN_DATE));
        dateTimeFormatterRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(DEFAULT_FORMAT_PATTERN_DATETIME));
        //dateTimeFormatterRegistrar.setTimeFormatter(DateTimeFormatter.ofPattern(DEFAULT_FORMAT_PATTERN_TIME));
        dateTimeFormatterRegistrar.registerFormatters(conversionService);
        DateFormatterRegistrar dateFormatterRegistrar = new DateFormatterRegistrar();
        dateFormatterRegistrar.setFormatter(new DateFormatter(DEFAULT_FORMAT_PATTERN_DATE));
        dateFormatterRegistrar.registerFormatters(conversionService);
        return conversionService;
    }

}
