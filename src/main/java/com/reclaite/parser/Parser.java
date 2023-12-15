package com.reclaite.parser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.reclaite.component.ProductRepositoryProvider;
import com.reclaite.model.Product;
import com.reclaite.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Getter
@Service
@RequiredArgsConstructor
public abstract class Parser {
    
    @Autowired
    private ProductRepositoryProvider repositoryProvider;
    
    private final ParserType parserType;
    
    private final Map<ProductType, Function<Map<String, Object>, ? extends Product>> productMap = new HashMap<>();
    
    public abstract void processParser();
    
    public abstract Map<String, Object> getMatchedProduct(ProductType productType, String productName);
    
    public Product getProduct(ProductType productType, Map<String, Object> productMap) {
        return createProduct(productType, productMap);
    }
    
    public void registerParser(ProductType type, Function<Map<String, Object>, ? extends Product> consumer) {
        productMap.put(type, consumer);
    }
    
    public Product createProduct(ProductType productType, Map<String, Object> productMap) {
        Function<Map<String, Object>, ? extends Product> function = this.productMap.get(productType);
        if (function == null) {
            return null;
        }
        
        Product product = function.apply(productMap);
        product.setProductType(productType);
        
        return product;
    }
    
}
