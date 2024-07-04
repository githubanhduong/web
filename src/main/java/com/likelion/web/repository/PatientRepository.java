package com.likelion.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.web.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}

