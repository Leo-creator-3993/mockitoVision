package com.future.rocket.mockito.vision.model;

public class OrderService {

    private final InventoryService inventoryService;

    public OrderService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public String createOrder(String productId, int quantity) {
        if (inventoryService.isStockAvailable(productId, quantity)) {
            return "Order created successfully!";
        } else {
            throw new IllegalArgumentException("Insufficient stock for product: " + productId);
        }
    }
}
