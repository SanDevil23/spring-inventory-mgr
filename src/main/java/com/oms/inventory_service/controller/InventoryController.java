package com.oms.inventory_service.controller;

import com.oms.inventory_service.dto.ProductDto;
import com.oms.inventory_service.models.Product;
import com.oms.inventory_service.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")       // CORS resolution **not recommended for production
@Validated
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/inventory")
@RestController
public class InventoryController {

    // TODO: getProductById
    // TODO: getProductByCategory
    // TODO: getProductByName
    // TODO: UpdateQuantity
    // TODO: deleteProduct

    private final IProductService productService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto product){
        ProductDto newProduct = productService.add(product);
        if (newProduct==null){
            return ResponseEntity.badRequest().body("Failed to add a new product");
        }

        return ResponseEntity.ok().body("New Product added successfully");
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@RequestHeader long productId){
        return productService.getProductById(productId);
    }

    @GetMapping("/{category}")
    public List<ProductDto> getProductsByCategory(@RequestHeader String category){
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/{name}")
    public List<ProductDto> getProductsByName(@RequestHeader String name){
        return productService.getProductsByName(name);
    }

    @PatchMapping("/{id}/update/{qty}")
    public ResponseEntity<String> updateQty(@RequestHeader long id, @RequestHeader long qty){
        HttpStatus status = productService.updateQuantity(id, qty);
        if (status == HttpStatus.INTERNAL_SERVER_ERROR)
            return ResponseEntity.internalServerError().body("Failed to update quantity");

        return ResponseEntity.ok().body("Product Quantity Updated Successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@RequestHeader long id){
        ProductDto deletedProduct = productService.delete(id);
        System.out.println(deletedProduct);     // to be logged to the client end
        return ResponseEntity.ok("Product deleted from the inventory");
    }

}
