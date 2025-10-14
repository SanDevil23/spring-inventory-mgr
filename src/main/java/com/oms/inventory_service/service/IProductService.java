package com.oms.inventory_service.service;

import com.oms.inventory_service.dto.ProductDto;
import com.oms.inventory_service.models.Product;

import java.util.List;

public interface IProductService {
    ProductDto add(ProductDto product);
    ProductDto getProductById(Long productId);
    List<ProductDto> getProductsByCategory(String category);
    List<ProductDto> getProductsByName(String name);
    void updateQuantity(long productId, long qty);
    ProductDto delete(long productId);
}
