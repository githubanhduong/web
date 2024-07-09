package com.likelion.web.repository;

import java.util.Iterator;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.web.model.Physician;

public interface PhysicianRepository extends JpaRepository<Physician, Integer> {

    // Iterator<Physician> getPhysicianList();
}

