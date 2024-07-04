package com.likelion.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.web.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
