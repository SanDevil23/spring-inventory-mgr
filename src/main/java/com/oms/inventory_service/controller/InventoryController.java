package com.oms.inventory_service.controller;

import com.oms.inventory_service.dto.ProductDto;
import com.oms.inventory_service.models.Product;
import com.oms.inventory_service.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class InventoryController {

    // TODO: getProductById
    // TODO: getProductByCategory
    // TODO: getProductByName
    // TODO: UpdateQuantity
    // TODO: deleteProduct

    private final IProductService productService;

    public ResponseEntity<String> addProduct(@RequestBody ProductDto product){
        ProductDto newProduct = productService.add(product);
        if (newProduct==null){
            return ResponseEntity.badRequest().body("Failed to add a new product");
        }

        return ResponseEntity.ok().body("New Product added successfully");
    }

    public ProductDto getProductById(@RequestHeader long productId){
        return productService.getProductById(productId);
    }

    public List<ProductDto> getProductsByCategory(@RequestHeader String category){
        List<ProductDto> products = productService.getProductsByCategory(category);
        return products;
    }

    public void getProductsByName(@RequestHeader String name){
        // to be defined
    }

    public ResponseEntity<String> updateQty(@RequestHeader long id, @RequestHeader long qty){
        HttpStatus status = productService.updateQuantity(id, qty);
        if (status == HttpStatus.INTERNAL_SERVER_ERROR)
            return ResponseEntity.internalServerError().body("Failed to update quantity");

        return ResponseEntity.ok().body("Product Quantity Updated Successfully");
    }

    public ResponseEntity<String> delete(@RequestHeader long id){
        ProductDto deletedProduct = productService.delete(id);
        System.out.println(deletedProduct);     // to be logged to the client end
        return ResponseEntity.ok("Product deleted from the inventory");
    }

}
