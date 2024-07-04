package com.likelion.web.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class TrainedIn implements Serializable {

    @EmbeddedId
    private TrainedInId id;

    @ManyToOne
    @MapsId("physician")
    @JoinColumn(name = "physician")
    private Physician physician;

    @ManyToOne
    @MapsId("treatment")
    @JoinColumn(name = "treatment")
    private Procedure treatment;

    private LocalDate certificationdate;
    private LocalDate certificationexpires;

    // Getters and Setters
}

@Embeddable
class TrainedInId implements Serializable {

    private Integer physician;
    private Integer treatment;

    // Getters and Setters, equals() and hashCode()
}
