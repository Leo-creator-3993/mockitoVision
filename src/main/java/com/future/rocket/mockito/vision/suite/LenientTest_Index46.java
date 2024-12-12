package com.future.rocket.mockito.vision.suite;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.lenient;

public class LenientTest_Index46 {

    @Test
    public void testLenientStubbing() {
        //创建mock对象
        FooService mockService = Mockito.mock(FooService.class);

        //配置存根（宽松模式）
        lenient().when(mockService.foo("foo")).thenReturn("leo");

        //即使没有调用这个存根,也不会报错
        System.out.println("This test passes even if lenient stubbing is unused.");
        System.out.println("Result ==> " + mockService.foo("foo"));
    }

    interface FooService {
        String foo(String input);
    }
}
