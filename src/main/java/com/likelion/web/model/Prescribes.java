package com.likelion.web.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class Prescribes implements Serializable {

    @EmbeddedId
    private PrescribesId id;

    @ManyToOne
    @MapsId("physician")
    @JoinColumn(name = "physician")
    private Physician physician;

    @ManyToOne
    @MapsId("patient")
    @JoinColumn(name = "patient")
    private Patient patient;

    @ManyToOne
    @MapsId("medication")
    @JoinColumn(name = "medication")
    private Medication medication;

    private LocalDateTime dateprescribed; // invalid identifier oracleDB
    private Integer appointment;
    private String dose;

    // Getters and Setters
}

@Embeddable
class PrescribesId implements Serializable {

    private Integer physician;
    private Integer patient;
    private Integer medication;

    // Getters and Setters, equals() and hashCode()
}
