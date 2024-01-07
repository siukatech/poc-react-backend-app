package com.siukatech.poc.react.backend.app.web.controller;

import com.siukatech.poc.react.backend.app.business.service.InstantMsgService;
import com.siukatech.poc.react.backend.app.business.service.NotiService;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@ProtectedApiV1Controller
public class InstantMsgController {

    private final InstantMsgService instantMsgService;

    public InstantMsgController(InstantMsgService instantMsgService) {
        this.instantMsgService = instantMsgService;
    }

}
