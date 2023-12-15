package com.reclaite.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Memory extends Product {
    
    private Integer capacity;
    
    private String type;
    
    private Integer speed;
    
}
