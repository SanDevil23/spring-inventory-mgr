package com.oms.inventory_service.service;

import com.oms.inventory_service.dao.ProductRepository;
import com.oms.inventory_service.dto.ProductDto;
import com.oms.inventory_service.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService{
    private final ProductRepository productRepository;

    @Autowired
    ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto add(ProductDto product) {
        // check if the product already exists
        if (productRepository.existsById(product.getProductId()))
            return null;

        // creating new Product if it does not exist
        Product newProduct = Product.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .brandName(product.getBrandName())
                .description(product.getDescription())
                .costPrice(product.getCostPrice())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product createdProduct = productRepository.save(newProduct);
        return ProductDto.toDto(createdProduct);
    }

    @Override
    public ProductDto fetchProductById(Long productId) {
        return ProductDto.toDto(productRepository.getReferenceById(productId));
    }

    @Override
    public List<ProductDto> getProductsByCategory(String category) {
        List<ProductDto> productByCategory = new ArrayList<>();

        for (Product p : productRepository.findAll()){
            if (p.getCategory().equals(category)) {
                productByCategory.add(ProductDto.toDto(p));
            }
        }
        return productByCategory;
    }

    // TODO: define logic for retrieving products by name
    @Override
    public List<ProductDto> getProductsByName(String name) {
        return List.of();
    }

    @Override
    public void updateQuantity(long productId, long qty) {
        Product productToUpdate = productRepository.getReferenceById(productId);
        productToUpdate.setQuantity(qty);

        // verification mech
        if (productRepository.getReferenceById(productId).getQuantity() != qty) {
            // TODO: need to log quantity not updated
            return;
        }

        return;
    }

    @Override
    public ProductDto delete(long productId) {
        ProductDto productToDelete = ProductDto.toDto(productRepository.getReferenceById(productId));
        productRepository.deleteById(productId);
        return productToDelete;
    }
}
