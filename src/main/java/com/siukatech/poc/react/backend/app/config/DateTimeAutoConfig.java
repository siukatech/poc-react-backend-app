package com.siukatech.poc.react.backend.app.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

/**
 *
 * this is not working!!!
 *
 * This is not working unless adding this configuration to META-INF/spring.factories with key EnableAutoConfiguration, like this
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
 * com.siukatech.poc.react.backend.app.config.Config1,\
 * com.siukatech.poc.react.backend.app.config.Config2,\
 * com.siukatech.poc.react.backend.app.config.Config3
 *
 * references:
 * https://medium.com/@andylke/rest-controller-configuration-date-time-format-in-json-response-201e97aa74b0
 * https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/boot-features-developing-auto-configuration.html
 */

@Deprecated
@Slf4j
//@Configuration
//@AutoConfiguration(before = {JacksonAutoConfiguration.class})
public class DateTimeAutoConfig {
    private final Logger logger = LoggerFactory.getLogger(DateTimeAutoConfig.class);
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        logger.debug("jackson2ObjectMapperBuilderCustomizer - test");
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                final String dateFormat = "dd/MM/yyyy";
                final String timeFormat = "hh:mm:ss a";
                final String dateTimeFormat = dateFormat + " " + timeFormat;
                logger.debug("jackson2ObjectMapperBuilderCustomizer - customize: [{}]", dateTimeFormat);
                jacksonObjectMapperBuilder
                        .serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)))
                        .deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)))
                        .serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)))
                        .deserializers(new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timeFormat)))
                        .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
                        .deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
                        ;
            }
        };
    }
}
