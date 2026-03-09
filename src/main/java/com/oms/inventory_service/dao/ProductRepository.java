package com.oms.inventory_service.dao;

import com.oms.inventory_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM Product p WHERE p.productName LIKE %:name% OR p.brandName like %:name%", nativeQuery = true)
    List<Product> findByName(@Param("name") String name);
}
