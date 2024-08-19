package com.siukatech.poc.react.backend.app.base.web.controller.extended;

import com.siukatech.poc.react.backend.parent.business.service.UserService;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Deprecated
//@ProtectedApiV1Controller
public class MyController extends com.siukatech.poc.react.backend.parent.web.controller.MyController {

//    private final UserService userService;
    public MyController(UserService userService) {
        super(userService);
    }

    @GetMapping("/my/public-key")
    public ResponseEntity<?> getPublicKey(@RequestHeader HttpHeaders httpHeaders
            , Authentication authentication) {
        return super.getPublicKey(httpHeaders, authentication);
    }

    @GetMapping("/my/key-info")
    public ResponseEntity<?> getKeyInfo(@RequestHeader HttpHeaders httpHeaders
            , Authentication authentication) {
        return super.getKeyInfo(httpHeaders, authentication);
    }

    @GetMapping("/my/user-info")
    public ResponseEntity<?> getUserInfo(@RequestHeader HttpHeaders httpHeaders
            , Authentication authentication) {
        return super.getUserInfo(httpHeaders, authentication);
    }

    @GetMapping("/my/permissions")
    public ResponseEntity<?> getUserPermissions(@RequestHeader HttpHeaders httpHeaders
            , @RequestParam String appMid
            , Authentication authentication) {
        return super.getUserPermissions(httpHeaders, appMid, authentication);
    }

    @GetMapping("/my/user-view")
    public ResponseEntity<?> getUserView(@RequestHeader HttpHeaders httpHeaders
            , Authentication authentication) {
        return super.getUserView(httpHeaders, authentication);
    }

}
