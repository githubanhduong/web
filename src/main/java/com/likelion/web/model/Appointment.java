package com.likelion.web.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class Appointment {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "physician")
    private Physician physician;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "prepurse")
    private Nurse prepurse;

    @Column(name = "start_dt_time", nullable = false)
    private LocalDateTime start;

    @Column(name = "end_dt_time", nullable = false)
    private LocalDateTime end;

    @Column(name = "examinationroom", nullable = false)
    private String examinationRoom;
}

// /**
// * AppointmentId
// */
// @Embeddable
// class AppointmentId {

// private Integer physician;
// private Integer patient;
// private Integer department;
// private LocalDateTime date;

// }
