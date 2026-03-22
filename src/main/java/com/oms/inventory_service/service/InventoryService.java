package com.oms.inventory_service.service;

import com.oms.inventory_service.dao.ProductRepository;
import com.oms.inventory_service.models.Product;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements IInventoryService {
    private final ProductRepository repository;

    @Autowired
    InventoryService(ProductRepository repository){
        this.repository = repository;
    }

    @Override
    public boolean updateStock(long productId, long qty) {
        Product p = repository.getReferenceById(productId);
        try {
            long currQty = p.getQuantity();
            if (currQty<qty) throw new ArithmeticException("Quantity Insufficient");
            currQty -= qty;
            if (currQty == 0) {
                System.out.println("Product:" + productId + ": is out of stock" );
            }
            p.setQuantity(currQty);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
