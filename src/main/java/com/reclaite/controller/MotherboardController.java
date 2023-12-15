package com.reclaite.controller;

import lombok.RequiredArgsConstructor;
import com.reclaite.model.Motherboard;
import com.reclaite.model.Product;
import com.reclaite.model.UserInfo;
import com.reclaite.service.MotherboardService;
import com.reclaite.service.ComponentService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/motherboard")
@RequiredArgsConstructor
public class MotherboardController {
    
    private final ComponentService componentService;
    private final MotherboardService motherboardService;
    
    @GetMapping
    public List<Motherboard> getAllMotherboards() {
        return motherboardService.getMotherboardRepository().findAll();
    }
    
    @PostMapping("/filtered")
    public List<Motherboard> getFilteredMotherboards(@RequestBody UserInfo userInfo) {
        List<Product> products = componentService.getSelectedProducts(userInfo.getSelected());
        return motherboardService.getMotherboardRepository().findAll().stream().filter(
            motherboard -> {
                if (userInfo.getConfigurationType().getName() != motherboard.getConfigurationType()) {
                    return false;
                }
                for (Product product : products) {
                    if (!product.isCompatible(userInfo, motherboard)) {
                        return false;
                    }
                }
                return true;
            }
        ).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public Motherboard getMotherboardById(@PathVariable Long id) {
        return motherboardService.getMotherboardRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException("Motherboard not found with id " + id));
    }
    
    @PostMapping
    public Motherboard createMotherboard(@RequestBody Motherboard cpu) {
        return motherboardService.getMotherboardRepository().save(cpu);
    }
    
    @PutMapping("/{id}")
    public Motherboard updateMotherboard(@PathVariable Long id, @RequestBody Motherboard cpuDetails) {
        return motherboardService.updateMotherboard(id, cpuDetails);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMotherboard(@PathVariable Long id) {
        try {
            motherboardService.deleteMotherboard(id);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
