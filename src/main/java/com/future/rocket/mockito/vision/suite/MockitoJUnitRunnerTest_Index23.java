package com.future.rocket.mockito.vision.suite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoJUnitRunnerTest_Index23 {

    @Mock
    private Dependency dependency;

    @InjectMocks
    private MyService myService;

    @Test
    public void testProcessWithMockedDependency() {
        when(dependency.getData()).thenReturn("Lake");
        String result = myService.process();
        System.out.println("Result:" + result);
        verify(dependency, times(1)).getData();
        assertEquals("Process: ", result, dependency.getData());
    }

    public static class MyService {
        private final Dependency dependency;

        public MyService(Dependency dependency) {
            this.dependency = dependency;
        }

        public String process() {
            return dependency.getData();
        }
    }

    public static class Dependency {
        public String getData() {
            return "realData";
        }
    }
}
