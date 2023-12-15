package com.reclaite.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.reclaite.model.ComputerCase;
import com.reclaite.repository.ComputerCaseRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class ComputerCaseService {
    
    private final ComputerCaseRepository computerCaseRepository;
    
    public ComputerCase createCase(ComputerCase cpu) {
        return computerCaseRepository.save(cpu);
    }
    
    public ComputerCase updateCase(Long id, ComputerCase computerCaseDetails) {
        ComputerCase updatedComputerCase = computerCaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Case not found with id " + id));
        
        updatedComputerCase.setTitle(computerCaseDetails.getTitle());
        updatedComputerCase.setPrice(computerCaseDetails.getPrice());
        updatedComputerCase.setMaterial(computerCaseDetails.getMaterial());
        updatedComputerCase.setImages(computerCaseDetails.getImages());
        updatedComputerCase.setOtherSpecifications(computerCaseDetails.getOtherSpecifications());
        
        return computerCaseRepository.save(updatedComputerCase);
    }
    
    public ComputerCase deleteCase(Long id) {
        ComputerCase computerCase = computerCaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Case not found with id " + id));
        computerCaseRepository.delete(computerCase);
        return computerCase;
    }
    
}
