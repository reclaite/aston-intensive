package com.reclaite.controller;

import lombok.RequiredArgsConstructor;
import com.reclaite.model.CPU;
import com.reclaite.model.ConfigurationType;
import com.reclaite.model.Motherboard;
import com.reclaite.repository.MotherboardRepository;
import com.reclaite.service.CPUService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final CPUService cpuService;
    private final MotherboardRepository motherboardRepository;
    
    @GetMapping("/types")
    public List<ConfigurationType.DTO> getTypes() {
        return Arrays.stream(ConfigurationType.values())
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    private ConfigurationType.DTO mapToDto(ConfigurationType configurationType) {
        return new ConfigurationType.DTO(configurationType, configurationType.getTitle());
    }
    
    @GetMapping("/cpu")
    public List<CPU> getCPUs() {
        return cpuService.getCpuRepository().findAll();
    }
    
    @GetMapping("/motherboard")
    public List<Motherboard> getMotherboards() {
        return motherboardRepository.findAll();
    }
}
