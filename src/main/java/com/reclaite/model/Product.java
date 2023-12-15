package com.reclaite.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@MappedSuperclass
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    private double price;
    
    @ElementCollection
    private List<String> images;
    
    @ElementCollection
    private Map<String, String> otherSpecifications;
    
    private ProductType productType;
    
    private ConfigurationType configurationType;
    
    public boolean isCompatible(UserInfo userInfo, Product product) {
        return true;
    }
    
}
