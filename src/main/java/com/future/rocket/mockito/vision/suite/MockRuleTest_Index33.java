package com.future.rocket.mockito.vision.suite;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class MockRuleTest_Index33 {

    //作用同@RunWith(MockitoJUnitRunner.class)
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    private FooService fooService;

    @InjectMocks
    private BarService barService;

    @Test
    public void test0() {
        when(fooService.foo()).thenReturn("foo foo");
        String result = barService.bar();
        System.out.println("Result ==> " + result);
    }

    public static class FooService {
        public String foo() {
            return "foo";
        }
    }

    public static class BarService {
        private FooService fooService;

        public String bar() {
            return fooService.foo();
        }
    }
}
