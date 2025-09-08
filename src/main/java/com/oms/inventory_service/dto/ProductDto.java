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
    private Long productId;

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
                product.getProductId(),
                product.getProductName(),
                product.getBrandName(),
                product.getBrandName(),
                product.getCategory(),
                product.getCostPrice()
        );
    }
}
