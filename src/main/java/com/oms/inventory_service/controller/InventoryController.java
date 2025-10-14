package com.oms.inventory_service.controller;

import com.oms.inventory_service.dto.ProductDto;
import com.oms.inventory_service.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class InventoryController {
    private final IProductService productService;

    public ResponseEntity<String> addProduct(@RequestBody ProductDto product){
        ProductDto newProduct = productService.add(product);
        if (newProduct==null){
            return ResponseEntity.badRequest().body("Failed to add a new product");
        }

        return ResponseEntity.ok().body("New Product added successfully");
    }
}
