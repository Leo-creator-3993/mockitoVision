package com.future.rocket.mockito.vision.suite;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class AbstractClassTest_Index30 {

    public abstract class AbstractService {
        public abstract String foo();
    }

    @Test
    public void testSpyAbstractClass() {
        AbstractService abstractService = mock(AbstractService.class);
        when(abstractService.foo()).thenReturn("foo");

        String result = abstractService.foo();
        System.out.println(result);
        verify(abstractService, times(1)).foo();
    }
}
