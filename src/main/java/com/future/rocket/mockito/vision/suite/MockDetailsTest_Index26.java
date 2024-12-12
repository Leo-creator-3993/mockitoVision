package com.future.rocket.mockito.vision.suite;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockingDetails;

import java.util.List;

import static org.mockito.Mockito.*;

public class MockDetailsTest_Index26 {

    private List<String> mockList;

    @Before
    public void setUp() {
        mockList = mock(List.class);
        mockList.add("A");
        mockList.add("B");
        mockList.add("C");
    }

    @Test
    public void testMockingDetails() {
        when(mockList.get(0)).thenReturn("LEO");
        System.out.println("result: " + mockList.get(0));

        MockingDetails mockingDetails = mockingDetails(mockList);
        System.out.println("Is mock: " + mockingDetails.isMock());
        System.out.println("Is spy: " + mockingDetails.isSpy());

        System.out.println("Invocations: " + mockingDetails.getInvocations());
        System.out.println("Stubbing: " + mockingDetails.getStubbings());
    }
}
