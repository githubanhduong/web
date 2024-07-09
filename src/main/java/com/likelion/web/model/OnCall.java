package com.likelion.web.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@IdClass(OnCallId.class) // Specify the composite key class
public class OnCall {

    @Id
    @ManyToOne
    @JoinColumn(name = "nurse")
    private Nurse nurse;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "blockfloor", nullable = false)
    @JoinColumn(name = "blockcode", nullable = false)
    private Block block;

    @Id
    @Column(name = "oncallstart", nullable = false)
    private LocalDateTime start;

    @Id
    @Column(name = "oncallend", nullable = false)
    private LocalDateTime end;

}

class OnCallId implements Serializable {
    private Nurse nurse;
    private Block block;
    private LocalDateTime start;
    private LocalDateTime end;

    // constructors, equals, hashCode methods
}
