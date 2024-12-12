package com.future.rocket.mockito.vision.suite;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class DelegateMockTest_Index27 {
    @Test
    public void test0() {
        DelegateService delegateRealService = new DelegateRealServiceImpl();
        DelegateService spyService = spy(delegateRealService);
        System.out.println("#1 result: " + spyService.foo());
        when(spyService.foo()).thenReturn("leo");
        System.out.println("#2 result: " + spyService.foo());
    }

    interface DelegateService {
        String foo();
    }

    class DelegateRealServiceImpl implements DelegateService {
        @Override
        public String foo() {
            return "leo foo!";
        }
    }
}