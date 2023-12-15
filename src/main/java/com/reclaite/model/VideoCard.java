package com.reclaite.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class VideoCard extends Product {
    
    private Integer memorySize;
    
    private Integer frequency;
    
    private String memoryType;
    
}
