package com.likelion.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Medication {

    @Id
    private Long code;
    private String name;
    private String brand;
    private String description;
}
