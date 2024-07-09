package com.likelion.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Nurse {

    @Id
    private Long id;

    private String name;
    private Integer ssn;
    private String position;
    private Boolean registered;
}
