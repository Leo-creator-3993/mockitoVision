package com.future.rocket.mockito.vision.suite;

import com.future.rocket.mockito.vision.model.InventoryServiceExtend;
import com.future.rocket.mockito.vision.model.OrderServiceExtend;
import com.future.rocket.mockito.vision.model.PaymentServiceExtend;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class OrderServiceExtendTest_Index24_25 {

    private OrderServiceExtend orderServiceExtend;

    private InventoryServiceExtend inventoryServiceExtend;

    private PaymentServiceExtend paymentServiceExtend;

    @Before
    public void setUp() {
        inventoryServiceExtend = mock(InventoryServiceExtend.class, invocation -> {
            if(invocation.getMethod().getName().equals("checkStock")) {
                if(invocation.getArguments()[0].equals("item123")) {
                    return true;
                }
            }
            return false;
        });

        paymentServiceExtend = mock(PaymentServiceExtend.class, invocation -> {
            if(invocation.getMethod().getName().equals("processPayment")) {
                if(invocation.getArguments()[0].equals(100.0)) {
                    return true;
                }
            }
            return false;
        });

        orderServiceExtend = new OrderServiceExtend(inventoryServiceExtend, paymentServiceExtend);
    }

    @Test
    public void testPlaceOrder_success() {
        boolean result = orderServiceExtend.placeOrder("item123", 100.0);
        System.out.println("result: " + result);
        verify(inventoryServiceExtend).checkStock("item123");
        verify(paymentServiceExtend).processPayment(100.0);
        assertTrue(result);
        verifyNoMoreInteractions(ignoreStubs(inventoryServiceExtend, paymentServiceExtend));
        verifyNoMoreInteractions(inventoryServiceExtend, paymentServiceExtend);
    }

    @Test
    public void testPlaceOrder_fail() {
        boolean result = orderServiceExtend.placeOrder("item123", 33.3);
        System.out.println("result: " + result);
        assertFalse(result);
    }
}
