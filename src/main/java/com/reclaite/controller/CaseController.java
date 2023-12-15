package com.reclaite.controller;

import lombok.RequiredArgsConstructor;
import com.reclaite.model.ComputerCase;
import com.reclaite.model.Product;
import com.reclaite.model.UserInfo;
import com.reclaite.service.ComputerCaseService;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/case")
@RequiredArgsConstructor
public class CaseController {
    
    private final ComponentService componentService;
    private final ComputerCaseService computerCaseService;
    
    @GetMapping
    public List<ComputerCase> getAllCases() {
        return computerCaseService.getComputerCaseRepository().findAll();
    }
    
    @PostMapping("/filtered")
    public List<ComputerCase> getFilteredCases(@RequestBody UserInfo userInfo) {
        List<Product> products = componentService.getSelectedProducts(userInfo.getSelected());
        return computerCaseService.getComputerCaseRepository().findAll().stream().filter(
            aCase -> {
                if (userInfo.getConfigurationType().getName() != aCase.getConfigurationType()) {
                    return false;
                }
                for (Product product : products) {
                    if (!product.isCompatible(userInfo, aCase)) {
                        return false;
                    }
                }
                return true;
            }
        ).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ComputerCase getCaseById(@PathVariable Long id) {
        return computerCaseService.getComputerCaseRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException("Case not found with id " + id));
    }
    
    @PostMapping
    public ComputerCase createCase(@RequestBody ComputerCase cpu) {
        return computerCaseService.getComputerCaseRepository().save(cpu);
    }
    
    @PutMapping("/{id}")
    public ComputerCase updateCase(@PathVariable Long id, @RequestBody ComputerCase cpuDetails) {
        return computerCaseService.updateCase(id, cpuDetails);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCase(@PathVariable Long id) {
        try {
            computerCaseService.deleteCase(id);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
    
}
