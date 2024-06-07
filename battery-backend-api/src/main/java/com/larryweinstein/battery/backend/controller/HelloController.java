package com.larryweinstein.battery.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping
    public String greeting(){
        return "Hello, yo!";
    }
}
