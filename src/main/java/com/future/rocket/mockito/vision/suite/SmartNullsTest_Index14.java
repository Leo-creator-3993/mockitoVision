package com.future.rocket.mockito.vision.suite;

import com.future.rocket.mockito.vision.utils.OtherTool;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.mock;

public class SmartNullsTest_Index14 {
    interface Foo {
        String foo();
        int random();
        Apple apple();
        boolean isGoal();
        String x1(int i);
        String x2(String s, int i);
    }

    @Test
    public void testSmartNulls() {
        Foo mockedFoo = mock(Foo.class, RETURNS_SMART_NULLS);
        System.out.println("==1:" + mockedFoo.foo());
        System.out.println("==2:" + mockedFoo.random());
        System.out.println("==3:" + mockedFoo.apple());
        System.out.println("==4:" + mockedFoo.isGoal());
        OtherTool.printSplitLine();
        Foo mockedDefaultFoo = mock(Foo.class);
        System.out.println("##1:"+ mockedDefaultFoo.foo());
        System.out.println("##2:"+ mockedDefaultFoo.random());
        System.out.println("##3:"+ mockedDefaultFoo.apple());
        System.out.println("##4:"+ mockedDefaultFoo.isGoal());
    }

    @Test
    public void testCustomAnswer() {
        Foo customAnswerFoo = mock(Foo.class, new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                if(args.length == 0) {
                    return "X";
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Object arg : args) {
                        sb.append(arg.toString())
                                .append("$$");
                    }
                    return sb.toString();
                }
            }
        });
        System.out.println("@@ ==> " +customAnswerFoo.x1(42));
        System.out.println("## ==> " +customAnswerFoo.x2("Y", 33));
        System.out.println("KK ==> " + customAnswerFoo.foo());
    }

    static class Apple {
        private final String name;

        public Apple(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
