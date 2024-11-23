package com.future.rocket.mockito.vision.suite;

import com.future.rocket.mockito.vision.model.Calculator;
import com.future.rocket.mockito.vision.utils.OtherTool;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

//Step12
public class CalculatorDoMethodsTest {

    @Test
    public void testDoReturn() {
        Calculator calculatorSpy = spy(new Calculator());

        //spy对象满足存根使用存根行为,不满足则调用真实方法
        doReturn(-1).when(calculatorSpy).add(100, 100);
        assertEquals(-1, calculatorSpy.add(100, 100));
        assertEquals(42, calculatorSpy.add(10, 32));
    }

    @Test
    public void testDoThrow() {
        Calculator calculatorSpy = spy(new Calculator());
        doThrow(new IllegalArgumentException("非法被除数")).when(calculatorSpy).divide(100, -999999);

        try {
            calculatorSpy.divide(100, -999999);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            calculatorSpy.divide(10, 0);
        } catch (Exception e) {
            System.out.println("##:" + e.getMessage());
        }

        doThrow(new RuntimeException("特别存根测试")).when(calculatorSpy).divide(42, 0);
        try{
            calculatorSpy.divide(42, 0);
        }catch (Exception e){
            System.out.println("%%==>" + e.getMessage());
        }
    }

    @Test
    public void testDoNothing() {
        Calculator calculatorSpy = spy(new Calculator());
        doNothing().when(calculatorSpy).printMessage("foo");
        OtherTool.printSplitLine();
        calculatorSpy.printMessage("foo"); //什么都不做
        calculatorSpy.printMessage("leo");
    }

    @Test
    public void testDoAnswer() {
        Calculator calculatorSpy = spy(new Calculator());
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                int a = (int)invocation.getArguments()[0];
                int b = (int)invocation.getArguments()[1];
                return a * a + b * b;
            }
        }).when(calculatorSpy).multiply(100, 100);
        assertEquals(20000, calculatorSpy.multiply(100, 100));
        assertEquals(10000, calculatorSpy.multiply(10, 1000));
    }

    @Test
    public void testDoCallRealMethod() throws Exception {
        Calculator mockCalculator = mock(Calculator.class);
        when(mockCalculator.add(100, 100)).thenReturn(10000);
        assertEquals(10000, mockCalculator.add(100, 100));

        doCallRealMethod().when(mockCalculator).add(100, 100);
        assertEquals(200, mockCalculator.add(100, 100));
    }
}
