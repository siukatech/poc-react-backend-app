package com.siukatech.poc.react.backend.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import static org.springframework.test.util.AssertionErrors.assertEquals;

//@SpringBootTest(properties = {
//		"client-id=XXX"
//		, "client-secret=XXX"
//		, "client-realm=react-backend-realm"
//		, "spring.profiles.active=dev"
//		, "oauth2.client.keycloak=http://localhost:38180"
////		, "oauth2.client.keycloak=XXX"
//})
//@OverrideAutoConfiguration(enabled = false)
//@AutoConfigureDataJpa
class ReactBackendAppTests {

	@Test
	void contextLoads() {
	}

}
