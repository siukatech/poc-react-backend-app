package com.siukatech.poc.react.backend.app;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestPropertySource;


@TestPropertySource(properties = {
        "spring.jpa.show-sql: true"
        , "spring.jpa.properties.hibernate.format_sql: true"
        , "spring.jpa.properties.hibernate.dialect: org.hibernate.dialect.H2Dialect"
        , "logging.level.org.springframework.data: TRACE"
})
//@ComponentScan(basePackages = {"com.siukatech.poc.react.backend.app.data.entity"})
public abstract class AbstractJpaTests extends AbstractUnitTests {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractUnitTests.class);

}
