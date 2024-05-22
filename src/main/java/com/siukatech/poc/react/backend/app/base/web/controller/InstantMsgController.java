package com.siukatech.poc.react.backend.app.base.web.controller;

import com.siukatech.poc.react.backend.app.base.business.service.InstantMsgService;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ProtectedApiV1Controller
public class InstantMsgController {

    private final InstantMsgService instantMsgService;

    public InstantMsgController(InstantMsgService instantMsgService) {
        this.instantMsgService = instantMsgService;
    }

}
