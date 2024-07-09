package com.likelion.web.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class AffiliatedWith implements Serializable {

    @EmbeddedId
    private AffiliatedWithId id;

    @ManyToOne
    @MapsId("physician")
    @JoinColumn(name = "physician")
    private Physician physician;

    @ManyToOne
    @MapsId("department")
    @JoinColumn(name = "department")
    private Department department;

    @Column(name = "primaryaffiliation")
    private Boolean primaryaffiliation;

    // Getters and Setters
}

@Embeddable
class AffiliatedWithId implements Serializable {

    @SuppressWarnings("unused")
    private Integer physician;
    @SuppressWarnings("unused")
    private Integer department;

    // Getters and Setters, equals() and hashCode()
}
