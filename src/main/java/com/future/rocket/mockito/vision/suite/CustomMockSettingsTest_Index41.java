package com.future.rocket.mockito.vision.suite;

import org.junit.Test;
import org.mockito.MockSettings;


import static org.mockito.Mockito.*;

public class CustomMockSettingsTest_Index41 {

    @Test
    public void test0() {
        MockSettings settings = withSettings().name("Leo-Foo");
        Runnable mockRunnable = mock(Runnable.class, settings);
        System.out.println("Mock object name: " + mockRunnable);
        mockRunnable.run();
        verify(mockRunnable).run();
    }
}
