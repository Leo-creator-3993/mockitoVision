package com.future.rocket.mockito.vision.suite;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MockitoAnnotationTest_Index38 {

    @Retention(RetentionPolicy.RUNTIME)
    public @interface ServiceType {
        String value();
    }

    @ServiceType("foo foo")
    public interface FooService {
        @Deprecated
        void oldMethod(String str);

        <U, K> void processData(U data, K key);
    }

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private FooService fooService;

    @Test
    public void testMockServiceWithAnnotationsAndGenerics() throws NoSuchMethodException {
        doNothing().when(fooService).oldMethod(anyString());
        doNothing().when(fooService).processData(any(), any());

        fooService.oldMethod("foo foo");
        fooService.processData("genericTest", "leo");

        ServiceType serviceType = fooService.getClass().getAnnotation(ServiceType.class);
        System.out.println("Service Type Value: " + serviceType.value());

        Method oldMethod = fooService.getClass().getDeclaredMethod("oldMethod", String.class);
        System.out.println("Old method has @Deprecated annotation: " + oldMethod.isAnnotationPresent(Deprecated.class));

        Method processData = fooService.getClass().getDeclaredMethod("processData", Object.class, Object.class);
        System.out.println("ProcessData method has generic type: " + processData.getGenericParameterTypes()[0] + ", " + processData.getGenericParameterTypes()[1]);

        fooService.processData("DD", "PP");

        verify(fooService, times(1)).oldMethod("foo foo");
        verify(fooService, times(2)).processData(any(), any());
    }
}
