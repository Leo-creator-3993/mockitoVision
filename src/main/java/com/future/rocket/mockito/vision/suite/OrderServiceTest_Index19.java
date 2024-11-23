package com.future.rocket.mockito.vision.suite;

import com.future.rocket.mockito.vision.model.InventoryService;
import com.future.rocket.mockito.vision.model.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

public class OrderServiceTest_Index19 {
    @Mock
    private InventoryService inventoryService;

    private OrderService orderService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.orderService = new OrderService(inventoryService);
    }

    @Test
    public void shouldCreateOrderWhenStockIsAvailable() {
        //Given
        String productId = "P12345";
        int quantity = 10;
        given(inventoryService.isStockAvailable(productId, quantity)).willReturn(true);

        //When
        String result = orderService.createOrder(productId, quantity);

        //Then
        assertEquals(result, "Order created successfully!");
    }

    @Test
    public void shouldCreateOrderWhenStockIsInvalid() {
        //Given
        String productId = "P12345";
        int quantity = 10;
        given(inventoryService.isStockAvailable(productId, quantity)).willReturn(false);

        //When
        try {
            String result = orderService.createOrder(productId, quantity);
        } catch (Exception e) {
            System.out.println("Exception happened, message ==> " + e.getMessage());
        }
    }
}
