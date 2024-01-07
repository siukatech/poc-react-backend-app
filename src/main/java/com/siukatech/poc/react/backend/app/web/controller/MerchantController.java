package com.siukatech.poc.react.backend.app.web.controller;

import com.siukatech.poc.react.backend.app.business.dto.MerchantDto;
import com.siukatech.poc.react.backend.app.business.service.MerchantService;
import com.siukatech.poc.react.backend.app.business.form.MerchantForm;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@ProtectedApiV1Controller
public class MerchantController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    //@CrossOrigin(origins = "*")
    @GetMapping("/merchants")
    public ResponseEntity<?> listMerchants() {
        List<MerchantDto> merchantDtoList = this.merchantService.findMerchantAll();
        return ResponseEntity.ok(merchantDtoList);
    }

    @GetMapping("/merchants/{targetMerchantId}")
    public ResponseEntity<?> getMerchantById(@PathVariable(required = true) Long targetMerchantId) {
        MerchantDto merchantDto = this.merchantService.findMerchantById(targetMerchantId);
        return ResponseEntity.ok(merchantDto);
    }

    @PostMapping(value = "/merchants")
    public ResponseEntity<?> createMerchant(@Valid @RequestBody MerchantForm merchantForm) {
        logger.debug("createMerchant - start");
        //
        MerchantDto merchantDto = this.merchantService.createMerchant(merchantForm);
        //
        logger.debug("createMerchant - end");
        return ResponseEntity.status(HttpStatus.CREATED).body(merchantDto);
    }

    @PutMapping("/merchants/{targetMerchantId}")
    public ResponseEntity<?> updateMerchant(@Valid @RequestBody MerchantForm merchantForm, @PathVariable(required = true) Long targetMerchantId) {
        MerchantDto merchantDto = this.merchantService.updateMerchant(merchantForm, targetMerchantId);
        return ResponseEntity.status(HttpStatus.OK).body(merchantDto);
    }

    @DeleteMapping("/merchants/{targetMerchantId}")
    public HttpStatus deleteMerchant(@PathVariable(required = true) Long targetMerchantId) {
        this.merchantService.deleteMerchant(targetMerchantId);
        return HttpStatus.OK;
    }

}

