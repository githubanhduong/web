package com.likelion.web.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentid;

    @SuppressWarnings("unused")
    private String name;

    @OneToOne
    @JoinColumn(name = "head")
    private Physician head;

    @OneToMany(mappedBy = "department")
    private Set<AffiliatedWith> affiliatedWith;

}

