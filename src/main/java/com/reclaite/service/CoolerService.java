package com.reclaite.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.reclaite.model.Cooler;
import com.reclaite.repository.CoolerRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class CoolerService {
    
    private final CoolerRepository coolerRepository;
    
    public Cooler createCooler(Cooler cpu) {
        return coolerRepository.save(cpu);
    }
    
    public Cooler updateCooler(Long id, Cooler coolerDetails) {
        Cooler cooler = coolerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cooler not found with id " + id));
        
        cooler.setTitle(coolerDetails.getTitle());
        cooler.setPrice(coolerDetails.getPrice());
        cooler.setSocket(coolerDetails.getSocket());
        cooler.setRpm(coolerDetails.getRpm());
        cooler.setNoiseLevel(coolerDetails.getNoiseLevel());
        cooler.setRadiatorSize(coolerDetails.getRadiatorSize());
        
        return coolerRepository.save(cooler);
    }
    
    public Cooler deleteCooler(Long id) {
        Cooler cooler = coolerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cooler not found with id " + id));
        coolerRepository.delete(cooler);
        return cooler;
    }
    
}
