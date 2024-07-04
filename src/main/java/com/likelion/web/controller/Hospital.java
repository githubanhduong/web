package com.likelion.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hospital {
    @GetMapping("/hospital")
    public String getHospital() {
        return "hospital";
    }
}
