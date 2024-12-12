package com.future.rocket.mockito.vision.suite;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class TimeoutVerificationTest_Index22 {
    @Test
    public void testAsyncInteraction() {
        Runnable mockRunnable = mock(Runnable.class);
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(500);
                mockRunnable.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        verify(mockRunnable, timeout(1000)).run();
    }

    @Test
    public void testMultiThreadInteraction() {
        Runnable mockedRunnable = mock(Runnable.class);
        new Thread(mockedRunnable::run).start();
        new Thread(mockedRunnable::run).start();
        verify(mockedRunnable, timeout(1000).times(2)).run();
    }
}
