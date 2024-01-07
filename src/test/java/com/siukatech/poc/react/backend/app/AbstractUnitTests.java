package com.siukatech.poc.react.backend.app;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.test.context.TestPropertySource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


@TestPropertySource(properties = {
        "client-id: XXX"
        , "client-secret: XXX"
        , "client-realm: react-backend-realm"
//		, "oauth2-client-keycloak: http://localhost:38180"
        , "oauth2-client-keycloak: XXX"
        , "spring.profiles.active: dev"
        , "logging.level.org.springframework.web: TRACE"
//        , "logging.level.com.siukatech.poc.react.backend.parent: TRACE"
        , "logging.level.com.siukatech.poc.react.backend.parent: DEBUG"
        , "logging.level.com.siukatech.poc.react.backend.app: DEBUG"
})
public abstract class AbstractUnitTests {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractUnitTests.class);

    // The InMemoryClientRegistrationRepository is used to mock the OAuth2 configuration
    @MockBean
    protected InMemoryClientRegistrationRepository clientRegistrationRepository;

    @BeforeAll
    public static void init() {
        final ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        //rootLogger.setLevel(Level.ALL);
        rootLogger.setLevel(Level.DEBUG);

        logger.debug("AbstractUnitTests.init............");

        // If sub-class has her own init, then super-class's init is required to trigger manually
//        AbstractTests.init();
    }

    @AfterAll
    public static void terminate() {
        logger.debug("AbstractUnitTests.terminate............");
    }

}
