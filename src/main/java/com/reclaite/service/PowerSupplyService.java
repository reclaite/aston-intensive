package com.reclaite.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.reclaite.model.PowerSupply;
import com.reclaite.repository.PowerSupplyRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class PowerSupplyService {
    
    private final PowerSupplyRepository powerSupplyRepository;
    
    public PowerSupply createPowerSupply(PowerSupply powerSupply) {
        return powerSupplyRepository.save(powerSupply);
    }
    
    public PowerSupply updatePowerSupply(Long id, PowerSupply powerSupplyDetails) {
        PowerSupply powerSupply = powerSupplyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PowerSupply not found with id " + id));
        
        powerSupply.setTitle(powerSupplyDetails.getTitle());
        powerSupply.setPrice(powerSupplyDetails.getPrice());
        powerSupply.setCaseType(powerSupplyDetails.getCaseType());
        powerSupply.setWattage(powerSupplyDetails.getWattage());
        
        return powerSupplyRepository.save(powerSupply);
    }
    
    public PowerSupply deletePowerSupply(Long id) {
        PowerSupply cooler = powerSupplyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PowerSupply not found with id " + id));
        powerSupplyRepository.delete(cooler);
        return cooler;
    }
    
}
