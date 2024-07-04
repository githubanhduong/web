package com.likelion.web.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Block {
    @EmbeddedId
    private BlockId id;
}

@Embeddable
class BlockId implements Serializable {

    private Integer blockfloor;
    private Integer blockcode;
}
