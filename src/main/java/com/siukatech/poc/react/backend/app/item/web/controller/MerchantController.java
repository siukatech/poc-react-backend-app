package com.siukatech.poc.react.backend.app.item.web.controller;

import com.siukatech.poc.react.backend.app.item.business.dto.MerchantDto;
import com.siukatech.poc.react.backend.app.item.business.form.MerchantForm;
import com.siukatech.poc.react.backend.app.item.business.service.MerchantService;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@ProtectedApiV1Controller
public class MerchantController {

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
        log.debug("createMerchant - start");
        //
        MerchantDto merchantDto = this.merchantService.createMerchant(merchantForm);
        //
        log.debug("createMerchant - end");
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

