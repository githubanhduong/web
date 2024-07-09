package com.likelion.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.web.model.Department;
import com.likelion.web.model.Physician;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findAllByHead(Physician head);
}
