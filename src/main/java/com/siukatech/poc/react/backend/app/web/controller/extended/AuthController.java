package com.siukatech.poc.react.backend.app.web.controller.extended;

import com.siukatech.poc.react.backend.parent.business.form.auth.LoginForm;
import com.siukatech.poc.react.backend.parent.business.form.auth.RefreshTokenForm;
import com.siukatech.poc.react.backend.parent.business.service.AuthService;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.PublicApiV1Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@Slf4j
@Deprecated
//@PublicApiV1Controller
public class AuthController extends com.siukatech.poc.react.backend.parent.web.controller.AuthController {

//    @Value("${security.oauth2.client.registration.keycloak.client-id}")
//    private String clientId;
//    @Value("${security.oauth2.client.registration.keycloak.client-secret}")
//    private String clientSceret;

    //    private final OAuth2ClientProperties oAuth2ClientProperties;
    private final RestTemplate oauth2ClientRestTemplate;
//    private final ObjectMapper objectMapper;

    public AuthController(
//            OAuth2ClientProperties oAuth2ClientProperties
//            ,
            RestTemplate oauth2ClientRestTemplate
            ,
//            , ObjectMapper objectMapper
            AuthService authService
    ) {
        super(authService);
//        super(oAuth2ClientProperties, oauth2ClientRestTemplate, objectMapper);
////        this.oAuth2ClientProperties = oAuth2ClientProperties;
        this.oauth2ClientRestTemplate = oauth2ClientRestTemplate;
////        this.objectMapper = objectMapper;
    }


    @GetMapping(value = "/auth/login/{clientName}")
    public void doAuthCodeLogin(HttpServletResponse response
            , @PathVariable String clientName, @RequestParam(required = false) String codeChallenge) {
//        OAuth2ClientProperties.Registration registration = this.oAuth2ClientProperties.getRegistration().get(clientName);
//        OAuth2ClientProperties.Provider provider = this.oAuth2ClientProperties.getProvider().get(clientName);
////        response_type=code&client_id=react-backend-client-01&scope=openid&redirect_uri=http://localhost:3000/redirect
//        String authUrl = new StringBuffer()
//                .append(provider.getAuthorizationUri())
//                .append("?")
//                .append("response_type").append("=").append("code")
//                .append("&")
//                .append("client_id").append("=").append(registration.getClientId())
//                .append("&")
//                .append("scope").append("=").append(String.join(",", registration.getScope()))
//                .append("&")
//                .append("redirect_uri").append("=").append(registration.getRedirectUri())
//                .toString()
//                ;
//        response.setHeader("Location", authUrl);
//        response.setStatus(302);
        super.doAuthCodeLogin(response, clientName, codeChallenge);
    }

//    @PostMapping(value = "/auth/token/{clientName}/{code}")
//    public ResponseEntity<?> doAuthCodeToken(@PathVariable String clientName
//            , @PathVariable String code, @RequestParam(required = false) String codeVerifier) {
//        log.debug("doAuthCodeToken - clientName: [{}], code: [{}], codeVerifier: [{}]", clientName, code, codeVerifier);
////        OAuth2ClientProperties.Registration registration = this.oAuth2ClientProperties.getRegistration().get(clientName);
////        OAuth2ClientProperties.Provider provider = this.oAuth2ClientProperties.getProvider().get(clientName);
//////        response_type=code&client_id=react-backend-client-01&scope=openid&redirect_uri=http://localhost:3000/redirect
////
////        // headers.set('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
////        // headers.set('Authorization', `Basic ${Buffer.from(`${client_id}:${client_secret}`)}`);
////
////        String tokenUrl = provider.getTokenUri();
////        HttpHeaders httpHeaders = new HttpHeaders();
////        String basicAuthorization = registration.getClientId() + ":" + registration.getClientSecret();
////        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(basicAuthorization.getBytes(StandardCharsets.UTF_8)) + "");
////        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
//////        TokenReq tokenReq = new TokenReq(registration.getClientId(), registration.getClientSecret()
//////                , registration.getRedirectUri(), registration.getAuthorizationGrantType(), code);
////        MultiValueMap<String, String> tokenReq = new LinkedMultiValueMap<String, String>();
////        tokenReq.add("client_id", registration.getClientId());
////        tokenReq.add("client_secret", registration.getClientSecret());
////        tokenReq.add("redirect_uri", registration.getRedirectUri());
////        tokenReq.add("grant_type", registration.getAuthorizationGrantType());
////        tokenReq.add("code", code);
////        HttpEntity<?> httpEntity = new HttpEntity<>(tokenReq, httpHeaders);
////
////        log.debug("doAuthCodeToken - clientName: [" + clientName
////                + "], code: [" + code
////                + "], tokenReq: [" + tokenReq
////                + "], httpHeaders: [" + httpHeaders
////                + "], oauth2ClientRestTemplate.toString: [" + oauth2ClientRestTemplate.toString()
////                + "], oauth2ClientRestTemplate.getMessageConverters.size: [" + oauth2ClientRestTemplate.getMessageConverters().size()
////                + "]");
////        oauth2ClientRestTemplate.getMessageConverters().stream().forEach(httpMessageConverter -> {
////            log.debug("doAuthCodeToken - httpMessageConverter.getClass.getName: [" + httpMessageConverter.getClass().getName() + "]");
////        });
////
////        ResponseEntity<TokenRes> responseEntity = oauth2ClientRestTemplate.exchange(tokenUrl
////                , HttpMethod.POST, httpEntity, TokenRes.class);
////
////        log.debug("doAuthCodeToken - clientName: [" + clientName
////                + "], code: [" + code
////                + "], tokenReq: [" + tokenReq
////                + "], responseEntity: [" + responseEntity
////                + "]");
////
////        return ResponseEntity.ok(responseEntity.getBody());
//        return super.doAuthCodeToken(clientName, code, codeVerifier);
//    }

    @PostMapping(value = "/auth/login/{clientName}")
    public ResponseEntity<?> doPasswordLogin(@PathVariable String clientName
            , @RequestBody LoginForm loginForm) {
        return super.doPasswordLogin(clientName, loginForm);
    }

    @PostMapping(value = "/auth/refresh-token/{clientName}")
    public ResponseEntity<?> doAuthTokenRefresh(@PathVariable String clientName
            , @RequestBody RefreshTokenForm refreshTokenForm) {
        return super.doAuthTokenRefresh(clientName, refreshTokenForm);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> doAuthLogout(HttpServletRequest request) throws URISyntaxException {
//        try {
//            String requestURL = request.getRequestURL().toString();
//            String requestURI = request.getRequestURI();
//            String hostName = requestURL.substring(0, requestURL.lastIndexOf(requestURI));
//            String logoutApi = hostName + "/logout";
//            log.debug("doAuthLogout - logoutApi: [{}]"
//                            + ", request.getLocalName: [{}]"
//                            + ", request.getLocalPort: [{}]"
//                            + ", request.getServerName: [{}]"
//                            + ", request.getServerPort: [{}]"
//                            + ", request.getPathInfo: [{}]"
//                            + ", request.getRequestURI: [{}]"
//                            + ", request.getProtocol: [{}]"
//                            + ", request.getRequestURL: [{}]"
//                    , logoutApi
//                    , request.getLocalName()
//                    , request.getLocalPort()
//                    , request.getServerName()
//                    , request.getServerPort()
//                    , request.getPathInfo()
//                    , request.getRequestURI()
//                    , request.getProtocol()
//                    , request.getRequestURL().toString()
//            );
//            Map map = this.oauth2ClientRestTemplate.postForObject(new URI(logoutApi), null, HashMap.class);
//
//        }
//        catch (Exception e) {
//            log.error("doAuthLogout", e);
//        }
//
//        return ResponseEntity.ok("OK");
        return super.doAuthLogout(request);
    }
}
