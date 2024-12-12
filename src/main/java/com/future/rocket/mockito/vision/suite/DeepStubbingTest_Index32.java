package com.future.rocket.mockito.vision.suite;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class DeepStubbingTest_Index32 {
    public class Service<T> {
        public T doSomething() {
            return null;
        }
    }

    public class Client {
        public Service<String> foo() {
            return new Service<>();
        }

        public Service<Integer> bar() {
            return new Service<>();
        }
    }

    @Test
    public void testDeepStubbing() {
        Client client = mock(Client.class, withSettings().defaultAnswer(CALLS_REAL_METHODS));
        Service<String> service = mock(Service.class);
        when(client.foo()).thenReturn(service);
        when(service.doSomething()).thenReturn("foo");
        String result = client.foo().doSomething();
        System.out.println("result ==> " + result);

        Service<Integer> service2 = mock(Service.class);
        when(client.bar()).thenReturn(service2);
        when(service2.doSomething()).thenReturn(42);
        int result2 = client.bar().doSomething();
        System.out.println("result2 ==> " + result2);
    }
}

