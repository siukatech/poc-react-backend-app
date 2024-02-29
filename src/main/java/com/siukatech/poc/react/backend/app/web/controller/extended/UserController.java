package com.siukatech.poc.react.backend.app.web.controller.extended;

import com.siukatech.poc.react.backend.parent.business.service.UserService;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Deprecated
//@ProtectedApiV1Controller
public class UserController extends com.siukatech.poc.react.backend.parent.web.controller.UserController {

//    private final UserRepository userRepository;
    private UserService userService;
    public UserController(UserService userService
//            , UserRepository userRepository
    ) {
        super(userService);
//        this.userRepository = userRepository;
    }

////    @PostMapping("/users/my/public-key")
////    public ResponseEntity<?> getMyPublicKey(Authentication authentication) {
////        String loginId = authentication.getName();
////        final String finalLoginId = loginId;
////        UserEntity userEntity = userRepository.findByLoginId(loginId)
////                .orElseThrow(() -> new EntityNotFoundException("No such user [" + finalLoginId + "]"));
////        ;
////        String publicKeyBase64 = userEntity.getPublicKey();
////        return ResponseEntity.ok(publicKeyBase64);
////    }
//    @PostMapping("/users/{targetLoginId}/public-key")
//    public ResponseEntity<?> getPublicKey(@PathVariable String targetLoginId) {
//        return super.getPublicKey(targetLoginId);
//    }


//    @PostMapping("/users/{loginId}")
//    public ResponseEntity<?> getPublicKey(Authentication authentication
//            , @PathVariable(name = "loginId") String reqLoginId) {
//        String loginId = authentication.getName();
//        final String finalLoginId = loginId;
//        UserEntity userEntity = userRepository.findByLoginId(loginId)
//                .orElseThrow(() -> new EntityNotFoundException("No such user [" + finalLoginId + "]"));
//        ;
//        String publicKeyBase64 = userEntity.getPublicKey();
//        return ResponseEntity.ok(publicKeyBase64);
//    }
    @PostMapping("/users/{targetLoginId}/user-info")
    public ResponseEntity<?> getUserInfo(@PathVariable(name = "targetLoginId") String reqLoginId) {
        return super.getUserInfo(reqLoginId);
    }

}
