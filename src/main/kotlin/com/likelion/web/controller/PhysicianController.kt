package com.likelion.web.controller

import lombok.Data
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

import com.likelion.web.service.HospitalService

@RestController
@Data
@RequiredArgsConstructor
class PhysicianController(val hospitalService: HospitalService) {
    @GetMapping("/physician")
    fun getPhysician(): List<String> {
        return emptyList()
    }
}
