package com.siukatech.poc.react.backend.app;

import com.siukatech.poc.react.backend.core.web.advice.mapper.ProblemDetailExtMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@SpringBootTest
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

	@MockBean
	private InMemoryClientRegistrationRepository clientRegistrationRepository;

	// After moving the following WebSecurityConfig Components from constructor to filterChain,
	// they are no longer required to mark as MockBean or MockitoBean
//	@MockBean
//	private OAuth2ClientProperties oAuth2ClientProperties;
//	@MockBean
//	private OAuth2ResourceServerProperties oAuth2ResourceServerProperties;
//	@MockBean
//	private JwtDecoder jwtDecoder;
	// @MockBean
	// private ProblemDetailExtMapper problemDetailExtMapper;

	@Test
	void contextLoads() {
	}

}
