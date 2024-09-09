package com.likelion.web.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likelion.web.model.AffiliatedWith;
import com.likelion.web.model.Department;
import com.likelion.web.model.Patient;
import com.likelion.web.model.Physician;
import com.likelion.web.repository.AffiliatedWithRepository;
import com.likelion.web.repository.DepartmentRepository;
import com.likelion.web.repository.PatientRepository;
import com.likelion.web.repository.PhysicianRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalService {
    private final PhysicianRepository physicianRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final AffiliatedWithRepository<AffiliatedWith> affiliatedWithRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public List<Physician> getPhysicianList() {
        // getDepartment();
        return physicianRepository.findAll();
    }

    public Department getDepartment() {
        Physician physician = physicianRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("Author not found"));
        log.info(physician.getDepartments().toString());
        return physician.getDepartments().get(0);
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

