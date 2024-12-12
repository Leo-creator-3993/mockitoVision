package com.future.rocket.mockito.vision.suite;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FinalMockTest_Index39 {

    //pom中增加依赖mockito-inline
    public final class FooFinalClass {
        public String foo() {
            return "##final foo##";
        }
    }

    @Test
    public void testFinalClass() {
        FooFinalClass mock = mock(FooFinalClass.class);
        when(mock.foo()).thenReturn("foo foo");
        System.out.println(mock.foo());
    }
}
