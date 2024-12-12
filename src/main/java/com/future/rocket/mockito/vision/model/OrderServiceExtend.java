package com.future.rocket.mockito.vision.model;

public class OrderServiceExtend {

    private final InventoryServiceExtend inventoryServiceExtend;
    private final PaymentServiceExtend paymentServiceExtend;

    public OrderServiceExtend(InventoryServiceExtend inventoryServiceExtend, PaymentServiceExtend paymentServiceExtend) {
        this.inventoryServiceExtend = inventoryServiceExtend;
        this.paymentServiceExtend = paymentServiceExtend;
    }

    public boolean placeOrder(String itemCode, double amount) {
        if(!inventoryServiceExtend.checkStock(itemCode)) {
            return false;//库存不足
        }

        if(!paymentServiceExtend.processPayment(amount)) {
            return false;//支付失败
        }
        return true;
    }

}
