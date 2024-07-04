package com.likelion.web.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Undergoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer patient;
    private Integer procedure;
    private Integer stay;
    private LocalDateTime dateUndergoes;
    
    @ManyToOne
    @JoinColumn(name = "physician")
    private Physician physician;
}
