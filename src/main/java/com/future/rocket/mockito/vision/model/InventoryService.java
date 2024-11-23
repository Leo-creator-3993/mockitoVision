package com.future.rocket.mockito.vision.model;

public interface InventoryService {
    boolean isStockAvailable(String productId, int quantity);
}
