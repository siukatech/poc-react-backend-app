package com.siukatech.poc.react.backend.app;

import com.siukatech.poc.react.backend.parent.security.authentication.MyAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWebTests extends AbstractUnitTests {

    protected MyAuthenticationToken prepareMyAuthenticationToken(String loginId, Long userId) {
        List<GrantedAuthority> convertedAuthorities = new ArrayList<GrantedAuthority>();
        Map<String, Object> attributeMap = new HashMap<>();
        attributeMap.put(StandardClaimNames.PREFERRED_USERNAME, loginId);
        attributeMap.put(MyAuthenticationToken.ATTR_TOKEN_VALUE, "TOKEN");
        attributeMap.put(MyAuthenticationToken.ATTR_USER_ID, userId);
        OAuth2User oAuth2User = new DefaultOAuth2User(convertedAuthorities, attributeMap, StandardClaimNames.PREFERRED_USERNAME);
        MyAuthenticationToken authenticationToken = new MyAuthenticationToken(oAuth2User, convertedAuthorities, "keycloak");
        return authenticationToken;
    }

}
