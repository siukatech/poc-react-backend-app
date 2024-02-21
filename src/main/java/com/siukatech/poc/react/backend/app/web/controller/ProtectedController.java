package com.siukatech.poc.react.backend.app.web.controller;


import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@ProtectedApiV1Controller
public class ProtectedController {
    @GetMapping("/test/{test}")
    public String test(@PathVariable String test) {
        return "Test [%s]".formatted(test);
    }
}
