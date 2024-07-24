package com.likelion.web.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.likelion.web.model.AffiliatedWith;
import com.likelion.web.model.Department;
import com.likelion.web.model.Patient;
import com.likelion.web.model.Physician;
import com.likelion.web.repository.AffiliatedWithRepository;
import com.likelion.web.repository.DepartmentRepository;
import com.likelion.web.repository.PatientRepository;
import com.likelion.web.repository.PhysicianRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final PhysicianRepository physicianRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final AffiliatedWithRepository<AffiliatedWith> affiliatedWithRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<Physician> getPhysicianList() {
        return physicianRepository.findAll();
    }

    @SuppressWarnings("unchecked")
    public <T> T saveAnyEntity(T physician) {
        
        if (physician instanceof Physician) {
            return (T) physicianRepository.save((Physician) physician);
        } else if (physician instanceof Patient) {
            return (T) patientRepository.save((Patient) physician);
        } else if (physician instanceof Department) {
            return (T) departmentRepository.save((Department) physician);
        } else if (physician instanceof AffiliatedWith) {
            return (T) affiliatedWithRepository.save((AffiliatedWith) physician);
        }
        return null;
    }

    public Physician savePhysician(Physician physician) {
        return physicianRepository.save(physician);
    }
}

