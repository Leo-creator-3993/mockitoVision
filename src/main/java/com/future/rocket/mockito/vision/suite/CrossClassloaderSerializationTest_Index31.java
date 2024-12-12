package com.future.rocket.mockito.vision.suite;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class CrossClassloaderSerializationTest_Index31 {

    public interface Service {
        String fetchData();
    }

    @Test
    public void testSerializableMock() {
        Service service = mock(Service.class, withSettings().serializable());
        when(service.fetchData()).thenReturn("foo foo");
        String result = service.fetchData();
        System.out.println("result: " + result);
    }
}
