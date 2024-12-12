package com.future.rocket.mockito.vision.suite;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class CustomAnswerTest_Index37 {

    public interface BarService {
        int processStrings(String s1, String s2, String s3);
    }

    @Test
    public void testCustomAnswer() {
        BarService mockBarService = mock(BarService.class);
        doAnswer(invocation -> {
            int firstParam = ((String)invocation.getArgument(0)).length();
            int secondParam = ((String)invocation.getArgument(1)).length();
            int thirdParam = ((String)invocation.getArgument(2)).length();
            return firstParam + secondParam + thirdParam;
        }).when(mockBarService).processStrings(anyString(), anyString(), anyString());

        int length = mockBarService.processStrings("leo", "foo", "bar");
        System.out.println("length ==> " + length);
    }
}
