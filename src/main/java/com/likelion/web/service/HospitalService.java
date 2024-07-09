package com.likelion.web.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.likelion.web.model.Physician;
import com.likelion.web.repository.PhysicianRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final PhysicianRepository physicianRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<Physician> getPhysicianList() {
        return physicianRepository.findAll();
    }

}
