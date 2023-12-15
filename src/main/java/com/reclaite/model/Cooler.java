package com.reclaite.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Cooler extends Product {
    
    @ElementCollection
    private List<String> socket;
    
    private Integer rpm;
    
    private Integer noiseLevel;
    
    private Integer radiatorSize;
    
    @Override
    public boolean isCompatible(UserInfo userInfo, Product product) {
        boolean flag = super.isCompatible(userInfo, product);
        if (product instanceof CPU) {
            flag = socket.contains(((CPU) product).getSocket());
        }
        
        if (product instanceof Motherboard) {
            flag = socket.contains(((Motherboard) product).getSocket());
        }
        
        return flag;
    }
}
