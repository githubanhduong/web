package com.likelion.web.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    private String name;
    private Double cost;

    @OneToMany(mappedBy = "procedure")
    private Set<Undergoes> undergoes;

    @OneToMany(mappedBy = "treatment")
    private Set<TrainedIn> trainedIn;

    // Getters and Setters
}

