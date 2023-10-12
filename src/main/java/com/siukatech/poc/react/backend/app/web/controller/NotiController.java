package com.siukatech.poc.react.backend.app.web.controller;

import com.siukatech.poc.react.backend.app.business.dto.ItemDto;
import com.siukatech.poc.react.backend.app.business.dto.NotiDto;
import com.siukatech.poc.react.backend.app.business.service.NotiService;
import com.siukatech.poc.react.backend.parent.business.dto.MyKeyDto;
import com.siukatech.poc.react.backend.parent.security.authentication.MyAuthenticationToken;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@ProtectedApiV1Controller
public class NotiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final NotiService notiService;

    public NotiController(NotiService notiService) {
        this.notiService = notiService;
    }

    //@CrossOrigin(origins = "*")
    @GetMapping("/notis")
    public ResponseEntity<?> listNotis(MyAuthenticationToken myAuthenticationToken, Pageable pageable) {
        Long userId = myAuthenticationToken.getUserId();
        Page<NotiDto> notiDtoPage = this.notiService.findNotiAllByUserId(userId, pageable);
        return ResponseEntity.ok(notiDtoPage);
    }

}
