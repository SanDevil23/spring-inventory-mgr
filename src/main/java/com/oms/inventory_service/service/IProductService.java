package com.oms.inventory_service.service;

import com.oms.inventory_service.models.Product;

import java.util.List;

public interface IProductService {
    Product add(Product product);
    Product fetchProductById(String productId);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByName(String name);
    void updateQuantity(String productId, int qty);
    Product delete(String productId);
}
