package com.reclaite.component;

import jakarta.annotation.PostConstruct;
import com.reclaite.model.Product;
import com.reclaite.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductRepositoryProvider {
    
    private final Map<ProductType, JpaRepository<? extends Product, Long>> repositoryMap;
    
    @Autowired
    public ProductRepositoryProvider(List<JpaRepository<? extends Product, Long>> repositories) {
        repositoryMap = new HashMap<>();
        repositories.forEach(repository -> {
            Class<?> entityType = getEntityType(repository);
            if (entityType != null) {
                ProductType productType = getProductType(entityType);
                if (productType != null) {
                    repositoryMap.put(productType, repository);
                }
            }
        });
    }
    
    @PostConstruct
    private void initialize() {
        // Проверка, что все необходимые репозитории были найдены
        if (repositoryMap.size() != ProductType.values().length) {
            throw new IllegalStateException("Not all repositories were found for product types");
        }
    }
    
    public JpaRepository<? extends Product, Long> getRepository(ProductType productType) {
        return repositoryMap.get(productType);
    }
    
    private Class<?> getEntityType(JpaRepository<? extends Product, Long> repository) {
        Class<?>[] typeArguments = GenericTypeResolver.resolveTypeArguments(repository.getClass(), JpaRepository.class);
        if (typeArguments != null && typeArguments.length >= 1) {
            return typeArguments[0];
        }
        return null;
    }
    
    private ProductType getProductType(Class<?> entityType) {
        try {
            String enumName = entityType.getSimpleName().toUpperCase();
            return ProductType.valueOf(enumName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
