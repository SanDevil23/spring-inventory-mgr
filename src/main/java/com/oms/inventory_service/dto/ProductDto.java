package com.oms.inventory_service.dto;

import com.oms.inventory_service.models.Product;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @Nonnull
    private String brandName;

    @Nonnull
    private String productName;

    private String description;
    private String costPrice;

    @Nonnull
    private String category;

    public static ProductDto toDto(Product product){

        return new ProductDto(
                product.getBrandName(),
                product.getProductName(),
                product.getDescription(),
                product.getCostPrice(),
                product.getCategory()
        );
    }
}
