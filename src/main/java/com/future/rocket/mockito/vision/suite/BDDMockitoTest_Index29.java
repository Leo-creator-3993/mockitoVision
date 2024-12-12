package com.future.rocket.mockito.vision.suite;

import com.future.rocket.mockito.vision.model.InventoryServiceExtend;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class BDDMockitoTest_Index29 {

    @Test
    public void test0() {
        InventoryServiceExtend inventoryServiceExtend = mock(InventoryServiceExtend.class);
        given(inventoryServiceExtend.checkStock("item123")).willReturn(true);

        boolean result = inventoryServiceExtend.checkStock("item123");
        System.out.println("result: " + result);
        then(inventoryServiceExtend).should().checkStock("item123");
        assertTrue(true);
    }
}
