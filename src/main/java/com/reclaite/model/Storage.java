package com.reclaite.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Storage extends Product {
    
    private Integer capacity;
    
    private String type;
    
    private String interfaceType;
    
    @Override
    public boolean isCompatible(UserInfo userInfo, Product product) {
        boolean flag = super.isCompatible(userInfo, product);
        
        if (product instanceof Motherboard) {
            flag = ((Motherboard) product).getStorageInterfaces().contains(interfaceType);
        }
        
        return flag;
    }
}
