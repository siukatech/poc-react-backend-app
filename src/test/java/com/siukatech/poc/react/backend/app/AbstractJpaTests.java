package com.siukatech.poc.react.backend.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestPropertySource;

//@Slf4j
@TestPropertySource(properties = {
        "spring.jpa.show-sql: true"
        , "spring.jpa.properties.hibernate.format_sql: true"
        , "spring.jpa.properties.hibernate.dialect: org.hibernate.dialect.H2Dialect"
        , "logging.level.org.springframework.data: TRACE"
})
//@ComponentScan(basePackages = {"com.siukatech.poc.react.backend.app.data.entity"})
public abstract class AbstractJpaTests extends AbstractUnitTests {

    protected static final Logger log = LoggerFactory.getLogger(AbstractUnitTests.class);

}
