package com.reclaite.configuration;

import com.reclaite.model.Product;
import com.reclaite.repository.CPURepository;
import com.reclaite.repository.ComputerCaseRepository;
import com.reclaite.repository.CoolerRepository;
import com.reclaite.repository.MemoryRepository;
import com.reclaite.repository.MotherboardRepository;
import com.reclaite.repository.PowerSupplyRepository;
import com.reclaite.repository.StorageRepository;
import com.reclaite.repository.VideoCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan("com.reclaite.component")
public class RepositoryConfiguration {
    
    @Autowired
    private ComputerCaseRepository computerCaseRepository;
    
    @Autowired
    private CoolerRepository coolerRepository;
    
    @Autowired
    private CPURepository cpuRepository;
    
    @Autowired
    private MemoryRepository memoryRepository;
    
    @Autowired
    private MotherboardRepository motherboardRepository;
    
    @Autowired
    private StorageRepository storageRepository;
    
    @Autowired
    private VideoCardRepository videoCardRepository;
    
    @Autowired
    private PowerSupplyRepository powerSupplyRepository;
    
    @Bean
    public List<JpaRepository<? extends Product, Long>> productRepositories() {
        return Arrays.asList(
            computerCaseRepository,
            coolerRepository,
            cpuRepository,
            memoryRepository,
            motherboardRepository,
            storageRepository,
            videoCardRepository,
            powerSupplyRepository
        );
    }
    
}
