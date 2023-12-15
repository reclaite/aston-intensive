package com.reclaite.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Motherboard extends Product {
    
    private String socket;
    
    private String caseType;
    
    private Integer ramSlots;
    
    private Integer memoryMax;
    
    private String supportedType;
    
    @ElementCollection
    private List<String> storageInterfaces;
    
    @Override
    public boolean isCompatible(UserInfo userInfo, Product product) {
        boolean flag = super.isCompatible(userInfo, product);
        
        if (product instanceof CPU) {
            flag = socket.equals(((CPU) product).getSocket());
        }
        
        if (product instanceof ComputerCase) {
            flag = caseType.equals(((ComputerCase) product).getType());
        }
        
        if (product instanceof Memory) {
            flag = supportedType.equals(((Memory) product).getType());
        }
        
        if (product instanceof Cooler) {
            flag = ((Cooler) product).getSocket().contains(socket);
        }
        
        return flag;
    }
}
