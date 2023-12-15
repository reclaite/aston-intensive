package com.reclaite.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.reclaite.model.Memory;
import com.reclaite.repository.MemoryRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class MemoryService {
    
    private final MemoryRepository memoryRepository;
    
    public Memory createMemory(Memory cpu) {
        return memoryRepository.save(cpu);
    }
    
    public Memory updateMemory(Long id, Memory memoryDetails) {
        Memory memory = memoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Memory not found with id " + id));
        
        memory.setTitle(memoryDetails.getTitle());
        memory.setPrice(memoryDetails.getPrice());
        memory.setCapacity(memoryDetails.getCapacity());
        memory.setSpeed(memoryDetails.getSpeed());
        memory.setImages(memoryDetails.getImages());
        memory.setOtherSpecifications(memoryDetails.getOtherSpecifications());
        
        return memoryRepository.save(memory);
    }
    
    public Memory deleteMemoryEntry(Long id) {
        Memory memory = memoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Memory not found with id " + id));
        memoryRepository.delete(memory);
        return memory;
    }
    
}
