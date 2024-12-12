package com.future.rocket.mockito.vision.suite;

import com.future.rocket.mockito.vision.utils.OtherTool;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class LambdaMatcherTest_Index36 {

    public interface FooService {
        String add(String msg);
    }

    @Test
    public void testLambdaMatcher() {
        FooService fooService = mock(FooService.class);
        fooService.add("leo leo");
        when(fooService.add(argThat(item -> item.contains("leo")))).thenReturn("matched");
        System.out.println("#1 ==> " + fooService.add("little leo"));
        System.out.println("#2 ==> " + fooService.add("foo"));
        OtherTool.printSplitLine();
        verify(fooService, times(2)).add(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String argument) {
                System.out.println("==> argument: " + argument);
                return argument.contains("leo");
            }
        }));
    }
}
