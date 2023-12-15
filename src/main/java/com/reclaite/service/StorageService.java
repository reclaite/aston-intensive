package com.reclaite.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.reclaite.model.Storage;
import com.reclaite.repository.StorageRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class StorageService {
    
    private final StorageRepository storageRepository;
    
    public Storage createStorage(Storage cpu) {
        return storageRepository.save(cpu);
    }
    
    public Storage updateStorage(Long id, Storage storageDetails) {
        Storage storage = storageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Storage not found with id " + id));
        
        storage.setTitle(storageDetails.getTitle());
        storage.setPrice(storageDetails.getPrice());
        storage.setCapacity(storageDetails.getCapacity());
        storage.setInterfaceType(storageDetails.getInterfaceType());
        storage.setImages(storageDetails.getImages());
        storage.setOtherSpecifications(storageDetails.getOtherSpecifications());
        
        return storageRepository.save(storage);
    }
    
    public Storage deleteStorage(Long id) {
        Storage storage = storageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Storage not found with id " + id));
        storageRepository.delete(storage);
        return storage;
    }
    
}
