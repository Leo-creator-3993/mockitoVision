package com.future.rocket.mockito.vision.suite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnswerTest {

    @Mock
    private Calculator mockCalculator;

    @Test
    public void testAnswer() {
        when(mockCalculator.add(anyInt(), anyInt())).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] arguments = invocationOnMock.getArguments();
                //获取参数并进行类型转换
                int a = (int) invocationOnMock.getArguments()[0];
                int b = (int) invocationOnMock.getArguments()[1];
                return a + b;
            }
        });

        System.out.println("rst1 ==> " + mockCalculator.add(10, 20));
        System.out.println("rst2 ==> " + mockCalculator.add(-1 , 1));
    }

    interface Calculator {
        int add(int a, int b);
    }
}
