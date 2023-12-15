package com.reclaite.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.reclaite.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ComponentService {
    
    @Getter
    private final int pageLimit = 50;
    
    private final CPUService cpuService;
    private final ComputerCaseService computerCaseService;
    private final CoolerService coolerService;
    private final MemoryService memoryService;
    private final MotherboardService motherboardService;
    private final StorageService storageService;
    private final VideoCardService videoCardService;
    
    public List<Product> getSelectedProducts(Map<String, Product> selectedTypes) {
        List<Product> products = new ArrayList<>();
        
        if (selectedTypes == null || selectedTypes.isEmpty()) {
            return products;
        }
        
        for (Map.Entry<String, Product> entry : selectedTypes.entrySet()) {
            final Product info = entry.getValue();
            switch (entry.getKey()) {
                case "cpu" -> products.add(cpuService.getCpuRepository().findById(info.getId()).orElse(null));
                case "cooler" -> products.add(coolerService.getCoolerRepository().findById(info.getId()).orElse(null));
                case "case" ->
                    products.add(computerCaseService.getComputerCaseRepository().findById(info.getId()).orElse(null));
                case "memory" -> products.add(memoryService.getMemoryRepository().findById(info.getId()).orElse(null));
                case "motherboard" ->
                    products.add(motherboardService.getMotherboardRepository().findById(info.getId()).orElse(null));
                case "storage" ->
                    products.add(storageService.getStorageRepository().findById(info.getId()).orElse(null));
                case "videocard" ->
                    products.add(videoCardService.getVideoCardRepository().findById(info.getId()).orElse(null));
            }
        }
        
        System.out.println(products);
        return products;
    }
    
}
