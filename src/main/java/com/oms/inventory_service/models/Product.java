package com.oms.inventory_service.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="_product")
@Entity
public class Product {

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "_order_id_sequence_generator"
    )
    @SequenceGenerator(
            name = "_product_id_sequence_generator",
            sequenceName = "_product_id_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @Id
    private Long productId;

    private String brandName;
    private String productName;
    private String description;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
