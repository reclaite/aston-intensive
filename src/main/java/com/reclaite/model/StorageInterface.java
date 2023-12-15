package com.reclaite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class StorageInterface {
    
    @Id
    private Long id;
    
    private String type;
    private Integer amount;
    
}
