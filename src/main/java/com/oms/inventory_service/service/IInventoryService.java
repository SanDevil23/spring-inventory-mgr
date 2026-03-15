package com.oms.inventory_service.service;

public interface IInventoryService {
    boolean updateStock(long productId, long qty);
}
