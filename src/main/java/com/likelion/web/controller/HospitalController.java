package com.likelion.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.web.model.Physician;
import com.likelion.web.service.HospitalService;
// import com.likelion.web.service.PhysicianService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@Slf4j
public class HospitalController {
    private final HospitalService hospitalService;
    // private final PhysicianService physicianService;

    @GetMapping("/hospital")
    public Mono<ResponseEntity<List<Physician>>> getHospital() {
        log.info("Fetched {} physicians from the service.", hospitalService.getPhysicianList());
        return Mono.just(ResponseEntity.ok().body(hospitalService.getPhysicianList()));
    }

    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    

    @PutMapping("path/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }

}
