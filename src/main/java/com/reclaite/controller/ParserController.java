package com.reclaite.controller;

import lombok.RequiredArgsConstructor;
import com.reclaite.component.ProductRepositoryProvider;
import com.reclaite.model.Product;
import com.reclaite.model.ProductType;
import com.reclaite.parser.Parser;
import com.reclaite.parser.ParserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/parser")
@RequiredArgsConstructor
public class ParserController {
    
    private final ProductRepositoryProvider repositoryProvider;
    
    @GetMapping("/{type}")
    public Product parseCreate(@PathVariable ProductType type, @RequestParam String name) {
        for (ParserType parserType : ParserType.getTypes()) {
            Parser parser = parserType.getParser();
            parser.processParser();
            Map<String, Object> productMap = parser.getMatchedProduct(type, name);
            Product product = parser.createProduct(type, productMap);
    
            JpaRepository<Product, Long> repository = (JpaRepository<Product, Long>) repositoryProvider.getRepository(type);
            repository.save(product);
            
            return product;
        }
        return null;
    }
}
