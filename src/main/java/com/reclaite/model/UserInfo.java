package com.reclaite.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@RequiredArgsConstructor
public class UserInfo {
    
    private Map<String, Product> selected;
    private ConfigurationType.DTO configurationType;
    
}
