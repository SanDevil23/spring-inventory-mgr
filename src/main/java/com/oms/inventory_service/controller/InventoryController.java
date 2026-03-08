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

    // TODO: getProductById
    // TODO: getProductByCategory
    // TODO: getProductByName
    // TODO: UpdateQuantity
    // TODO: deleteProduct

    private final IProductService productService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<APIResponse> addProduct(@RequestBody ProductDto product){
        ProductDto newProduct = productService.add(product);
        if (newProduct==null){
            return ResponseEntity.badRequest().body(APIResponse.error("Failed to add new product"));
        }

        return ResponseEntity.ok(APIResponse.success("New Product added successfully", product));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<APIResponse> getProductById(@RequestHeader long productId){
        ProductDto product = productService.getProductById(productId);
        return ResponseEntity.ok(APIResponse.success(HttpStatus.FOUND.toString(), product));
    }

    @GetMapping("/{category}")
    public ResponseEntity<APIResponse> getProductsByCategory(@RequestHeader String category){
        List<ProductDto> productsByCategory = productService.getProductsByCategory(category);
        return ResponseEntity.ok(APIResponse.success(HttpStatus.OK.toString(),productsByCategory));
    }

    @GetMapping("/{name}")
    public ResponseEntity<APIResponse> getProductsByName(@RequestHeader String name){
        List<ProductDto> productsByName = productService.getProductsByName(name);
        return ResponseEntity.ok(APIResponse.success(HttpStatus.OK.toString(), productsByName));
    }

    @PatchMapping("/{id}/update/{qty}")
    public ResponseEntity<APIResponse> updateQty(@RequestHeader long id, @RequestHeader long qty){
        HttpStatus status = productService.updateQuantity(id, qty);
        if (status == HttpStatus.INTERNAL_SERVER_ERROR)
            return ResponseEntity.internalServerError().body(APIResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.toString()));

        return ResponseEntity.ok(APIResponse.success("Product Updated Successfully", null));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> delete(@RequestHeader long id){
        ProductDto deletedProduct = productService.delete(id);
        System.out.println(deletedProduct);     // to be logged to the client end
        return ResponseEntity.ok(APIResponse.success(HttpStatus.OK.toString(), deletedProduct));
    }

}
