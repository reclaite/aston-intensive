package com.reclaite.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Map;

@Entity
@Data
public class CompleteBuild {
    
    @Id
    private String id;
    
    private String title;
    private ConfigurationType configurationType;
    
    @ElementCollection
    private Map<ProductType, Long> products;
    
}
