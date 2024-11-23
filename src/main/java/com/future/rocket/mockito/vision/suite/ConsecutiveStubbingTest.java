package com.future.rocket.mockito.vision.suite;

import com.future.rocket.mockito.vision.utils.OtherTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsecutiveStubbingTest {

    @Mock
    private Service mockservice;

    @Test
    public void testConsecutiveStubbing1() {
        when(mockservice.performAction("leo"))
                .thenReturn("foo1")
                .thenReturn("foo2")
                .thenReturn("foo3");

        System.out.println("1st ==> " + mockservice.performAction("leo"));
        System.out.println("2nd ==> " + mockservice.performAction("leo"));
        System.out.println("3trd ==> " + mockservice.performAction("leo"));
        OtherTool.printSplitLine();
        System.out.println("4th ==> " + mockservice.performAction("leo"));
        System.out.println("5x ==> " + mockservice.performAction("leo"));
    }

    @Test
    public void testConsecutiveStubbing2() {
        when(mockservice.performAction("leo"))
                .thenThrow(new RuntimeException("day etc."))
                .thenReturn("green mountain")
                .thenThrow(new IllegalArgumentException("road"));
        try {
            System.out.println(mockservice.performAction("leo"));
        } catch (RuntimeException e) {
            System.out.println("msg ==> " + e.getMessage());
        }

        System.out.println(mockservice.performAction("leo"));

        try{
            System.out.println(mockservice.performAction("leo"));
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal msg ==> " + e.getMessage());
        }

        try {
            mockservice.performAction("leo");
        } catch (IllegalArgumentException e) {
            System.out.println("x msg ==> " + e.getMessage());
        }
        System.out.println(mockservice.performAction("foo"));
    }


    interface Service {
        String performAction(String input);
    }
}
