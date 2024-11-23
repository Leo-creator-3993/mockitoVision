package com.future.rocket.mockito.vision.suite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContainerMockReset_Index17 {

    @Mock
    private FooService fooService;

    @Test
    public void testScenario1() {
        when(fooService.foo()).thenReturn("Result1");
        System.out.println("==: " + fooService.foo());
        reset(fooService);
        System.out.printf("##: " + fooService.foo());
    }

    @Test
    public void testScenario2() {
        List<String> mockedList = mock(List.class);
        when(mockedList.size()).thenReturn(99);
        System.out.println("Scenario 2 Size: " + mockedList.size());
        reset(mockedList);
        System.out.println("==: " + mockedList.size());
    }
}

interface FooService {
    String foo();
}