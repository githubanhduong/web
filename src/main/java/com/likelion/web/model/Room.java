package com.likelion.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    private Integer roomnumber;
    private String roomtype;
    private Boolean unavailable;

    @ManyToOne
    @JoinColumn(name = "blockfloor")
    @JoinColumn(name = "blockcode")
    private Block block;

}
