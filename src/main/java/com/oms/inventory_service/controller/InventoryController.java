package com.oms.inventory_service.controller;

import com.oms.inventory_service.dto.ProductDto;
import com.oms.inventory_service.models.Product;
import com.oms.inventory_service.service.IProductService;
import com.oms.inventory_service.util.APIResponse;
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

    private final IProductService productService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<APIResponse> addProduct(@RequestBody ProductDto product){
        ProductDto newProduct = productService.add(product);
        if (newProduct==null){
            return ResponseEntity.badRequest().body(APIResponse.error("Failed to add new product", HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(APIResponse.success("New Product added successfully", product, HttpStatus.CREATED));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<APIResponse> getProductById(@RequestHeader long productId){
        ProductDto product = productService.getProductById(productId);
        return ResponseEntity.ok(APIResponse.success("Product retrieved successfully.", product, HttpStatus.FOUND));
    }

    @GetMapping("/{category}")
    public ResponseEntity<APIResponse> getProductsByCategory(@RequestHeader String category){
        List<ProductDto> productsByCategory = productService.getProductsByCategory(category);
        return ResponseEntity.ok(APIResponse.success("Products for the selected category retrieved successfully.",productsByCategory, HttpStatus.OK));
    }

    @GetMapping("/{name}")
    public ResponseEntity<APIResponse> getProductsByName(@RequestHeader String name){
        List<ProductDto> productsByName = productService.getProductsByName(name);
        return ResponseEntity.ok(APIResponse.success("Products retrieved successfully.", productsByName, HttpStatus.OK));
    }

    @PatchMapping("/{id}/update/{qty}")
    public ResponseEntity<APIResponse> updateQty(@RequestHeader long id, @RequestHeader long qty){
        HttpStatus status = productService.updateQuantity(id, qty);
        if (status == HttpStatus.INTERNAL_SERVER_ERROR)
            return ResponseEntity.internalServerError().body(APIResponse.error("Attempt to update the product failed", HttpStatus.INTERNAL_SERVER_ERROR));

        return ResponseEntity.ok(APIResponse.success("Product Updated Successfully", null, HttpStatus.OK));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> delete(@RequestHeader long id){
        ProductDto deletedProduct = productService.delete(id);
        System.out.println(deletedProduct);     // to be logged to the client end
        return ResponseEntity.ok(APIResponse.success("Product Deleted Permanently", deletedProduct, HttpStatus.OK));
    }

}
