package com.future.rocket.mockito.vision.suite;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class ClearInlineMocksTest_Index47 {

    @Test
    public void testClearInlineMocks() {
        // 创建多个内联 Mock 对象
        MyFinalClass mock1 = mock(MyFinalClass.class);

        MyFinalClass realInstance = new MyFinalClass();
        MyFinalClass mock2 = Mockito.spy(realInstance);

        // 使用 Mock 对象
        System.out.println(mock1.doSomething());
        System.out.println(mock2.doSomething());

        // 清理 Mock 状态
        Mockito.framework().clearInlineMocks();

        // 此时所有内联 Mock 的状态已被清理，内存释放
        System.out.println("Inline mocks cleared.");
    }

    // 一个最终类（final class）
    public final class MyFinalClass {
        public MyFinalClass() {
            System.out.println("MyFinalClass constructor executed.");
        }

        public String doSomething() {
            return "Original implementation";
        }
    }
}
