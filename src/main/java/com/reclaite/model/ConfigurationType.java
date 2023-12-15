package com.reclaite.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ConfigurationType {
    
    OFFICE("Офисные задачи"),
    GRAPHICS("Графические задачи"),
    GAMING("Игровые задачи");
    
    private final String title;
    
    private static final ConfigurationType[] TYPES = ConfigurationType.values();
    
    public static ConfigurationType[] getTypes() {
        return TYPES;
    }
    
    @Data
    @RequiredArgsConstructor
    public static class DTO {
        
        private final ConfigurationType name;
        private final String title;
        
        public DTO() {
            this.name = null;
            this.title = null;
        }
        
    }
    
}
