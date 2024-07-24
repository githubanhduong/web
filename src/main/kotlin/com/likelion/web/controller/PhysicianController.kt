package com.likelion.web.controller

import lombok.Data
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody

import com.likelion.web.service.HospitalService
import com.likelion.web.model.Physician

@RestController
@Data
@RequiredArgsConstructor
@Slf4j
class PhysicianController(val hospitalService: HospitalService) {
    @GetMapping("/physician")
    fun getPhysician(): List<String> {
        return emptyList()
    }

    @PostMapping("/add/physician")
    fun postPhysician(@RequestBody physician: Physician): Physician {
        println("abc" + physician.toString());
        return hospitalService.savePhysician(physician)
    }


}
