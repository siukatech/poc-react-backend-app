package com.siukatech.poc.react.backend.app.web.controller.extended;

import com.siukatech.poc.react.backend.parent.business.service.UserService;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@ProtectedApiV1Controller
public class MyController extends com.siukatech.poc.react.backend.parent.web.controller.MyController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    private final UserService userService;
    public MyController(UserService userService) {
        super(userService);
    }

    @PostMapping("/my/public-key")
    public ResponseEntity<?> getPublicKey(Authentication authentication) {
        return super.getPublicKey(authentication);
    }

    @PostMapping("/my/key-info")
    public ResponseEntity<?> getKeyInfo(Authentication authentication) {
        return super.getKeyInfo(authentication);
    }

    @PostMapping("/my/user-info")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        return super.getUserInfo(authentication);
    }

}
