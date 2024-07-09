package com.likelion.web.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Block {
    @EmbeddedId
    private BlockId id;
}

@Embeddable
class BlockId {

    private Integer blockfloor;
    private Integer blockcode;
}
