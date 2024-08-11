package com.likelion.web.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.likelion.web.config.PhysicianSerializerConfig;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@JsonSerialize(using = PhysicianSerializerConfig.class)
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class Physician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeid;

    private String name;
    private String position;
    private Integer ssn;

    @OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, orphanRemoval=true)
    private List<Department> departments;

    @OneToMany(mappedBy = "physician")
    private Set<AffiliatedWith> affiliatedWith;

    @OneToMany(mappedBy = "physician")
    private Set<Prescribes> prescribes;

    @OneToMany(mappedBy = "physician")
    private Set<TrainedIn> trainedIn;

    @OneToMany(mappedBy = "physician")
    private Set<Undergoes> undergoes;

    @OneToMany(mappedBy = "physician")
    private Set<Patient> patients;

    @OneToMany(mappedBy = "physician")
    private Set<Appointment> appointments;

}
