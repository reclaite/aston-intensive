package com.reclaite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class CPU extends Product {
    
    private String socket;
    
    private Double frequency;
    
    @OneToOne
    private VideoCard integratedGraphics;
    
    private Integer energyConsumption;
    
    private Integer cores;
    
    private Integer threads;
    
    @Override
    public boolean isCompatible(UserInfo userInfo, Product product) {
        boolean flag = super.isCompatible(userInfo, product);
        if (product instanceof Motherboard) {
            flag = socket.equals(((Motherboard) product).getSocket());
        }
        
        if (product instanceof Cooler) {
            flag = ((Cooler) product).getSocket().contains(socket);
        }
    
        return flag;
    }
}