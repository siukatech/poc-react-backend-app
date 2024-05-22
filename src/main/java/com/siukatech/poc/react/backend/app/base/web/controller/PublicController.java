package com.siukatech.poc.react.backend.app.base.web.controller;


import com.siukatech.poc.react.backend.parent.web.annotation.v1.PublicApiV1Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@PublicApiV1Controller
public class PublicController {
    @GetMapping("/test/{test}")
    public String test(@PathVariable String test) {
        return "Test [%s]".formatted(test);
    }
}
