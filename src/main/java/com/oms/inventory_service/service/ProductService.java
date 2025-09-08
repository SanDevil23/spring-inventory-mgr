package com.oms.inventory_service.service;

import com.oms.inventory_service.dao.ProductRepository;
import com.oms.inventory_service.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    private final ProductRepository productRepository;

    @Autowired
    ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product add(Product product) {
        return null;
    }

    @Override
    public Product fetchProductById(String productId) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return List.of();
    }

    @Override
    public void updateQuantity(String productId, int qty) {

    }

    @Override
    public Product delete(String productId) {
        return null;
    }
}
