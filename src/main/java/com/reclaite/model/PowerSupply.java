package com.reclaite.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PowerSupply extends Product {
    
    private Integer wattage;
    
    private String caseType;
    
    @Override
    public boolean isCompatible(UserInfo userInfo, Product product) {
        boolean flag = super.isCompatible(userInfo, product);
        if (product instanceof ComputerCase) {
            flag = caseType.equals(((ComputerCase) product).getType());
        }
        
        if (product instanceof Motherboard) {
            flag = caseType.equals(((Motherboard) product).getCaseType());
        }
        
        return flag;
    }
    
}
