package com.future.rocket.mockito.vision.suite;

import org.junit.Test;
import org.mockito.Mockito;

public class CustomInstantiatorProviderTest_Index44 {

    @Test
    public void testCustomInstantiator() {
        MyService mockService = Mockito.mock(MyService.class);
        System.out.println("Mock created successfully: " + mockService);
    }

    static class MyService {
        public MyService() {
            System.out.println("MyService instantiated!");
        }
    }
}
