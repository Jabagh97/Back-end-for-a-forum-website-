package com.example.demo.controller;

import com.example.demo.service.InitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {

    private final InitService initService;

    public InitController(InitService initService) {
        this.initService = initService;
    }

    @PostMapping
    public void init() {
        initService.init();
    }
}
