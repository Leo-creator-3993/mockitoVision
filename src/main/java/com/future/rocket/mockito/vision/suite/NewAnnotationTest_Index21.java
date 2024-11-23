package com.future.rocket.mockito.vision.suite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewAnnotationTest_Index21 {

    @Mock
    private List<String> mockList;

    @Captor
    private ArgumentCaptor<String> captor;

    @Spy
    private List<String> spyList = new ArrayList<>();

    @Mock
    private Dependency dependency;

    @InjectMocks
    private Service service;

    @Test
    public void testCaptor() {
        mockList.add("leo");
        mockList.add("foo");

        verify(mockList, times(2)).add(captor.capture());

        List<String> captorValues = captor.getAllValues();
        assertEquals("leo", captorValues.get(0));
        assertEquals("foo", captorValues.get(1));
    }

    @Test
    public void testSpy() {
        spyList.add("lake");
        spyList.add("tree");

        assertEquals(2, spyList.size());
        assertEquals("lake", spyList.get(0));
        assertEquals("tree", spyList.get(1));

        doReturn("foo").when(spyList).get(0);
        doReturn("leo").when(spyList).get(1);
        assertEquals("foo", spyList.get(0));
        assertEquals("leo", spyList.get(1));
    }

    @Test
    public void testInjectMocks() {
        when(dependency.getData()).thenReturn("hi,leo");
        System.out.println("result ==> " + service.process());

        // 改为使用真实依赖对象
        Dependency realDependency = new Dependency(); // 创建真实对象
        Service realService = new Service(realDependency); // 创建真实 Service 对象
        Service spy = spy(realService); // 基于真实对象创建 Spy
        System.out.println("real result ==> " + spy.process());
    }

    // 被测试类
    static class Service {
        private final Dependency dependency;

        public Service(Dependency dependency) {
            this.dependency = dependency;
        }

        public String process() {
            return "Processed: " + dependency.getData();
        }
    }

    // 依赖类
    static class Dependency {
        public String getData() {
            return "Real Data";
        }
    }

}
