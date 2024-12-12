package com.future.rocket.mockito.vision.suite;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.exceptions.base.MockitoAssertionError;

import static org.mockito.Mockito.*;

public class CustomVerificationFailureMsgTest_Index35 {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    public interface FooService {
        String foo();
        void bar();
    }

    @Test
    public void testDescriptionUsage() {
        FooService mockedService = mock(FooService.class);
        when(mockedService.foo()).thenReturn("foo");

        System.out.println("Result ==> " + mockedService.foo());
        verify(mockedService, times(1).description("foo() should be invoke twice")).foo();
        verify(mockedService, never().description("bar() never invoke")).bar();

        //期望验证失败
        expectedException.expect(MockitoAssertionError.class);  // 捕获MockitoAssertionError
        expectedException.expectMessage("#1 ==> foo() should be invoke twice");  // 验证错误消息
        verify(mockedService, times(2).description("#1 ==> foo() should be invoke twice")).foo();
    }
}
