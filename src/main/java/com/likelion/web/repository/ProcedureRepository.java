package com.likelion.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.web.model.Procedure;

public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
}
