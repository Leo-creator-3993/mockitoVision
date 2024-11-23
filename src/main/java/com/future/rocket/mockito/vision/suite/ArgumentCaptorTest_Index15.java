package com.future.rocket.mockito.vision.suite;

import org.junit.Test;
import org.mockito.ArgumentCaptor;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ArgumentCaptorTest_Index15 {

    static class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    interface PersonService {
        void foo(Person person);

        String greet(Person name);
    }

    @Test
    public void testArgumentCaptor() {
        PersonService mockedPersonService = mock(PersonService.class);
        mockedPersonService.foo(new Person("Leo"));

        ArgumentCaptor<Person> argumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(mockedPersonService).foo(argumentCaptor.capture());
        System.out.println("==> " + argumentCaptor.getValue());
        assertEquals("Leo", argumentCaptor.getValue().getName());
    }

    @Test
    public void testArgumentMatcher() {
        PersonService mockedPersonService = mock(PersonService.class);
        when(mockedPersonService.greet(argThat(person -> "Leo".equals(person.name)))).thenReturn("Day Etc.");

        System.out.println("==>" + mockedPersonService.greet(new Person("Leo")));
        System.out.println("==>" + mockedPersonService.greet(new Person("RomBo")));

        //调用 void 方法
        mockedPersonService.foo(new Person("Leo"));
        mockedPersonService.foo(new Person("Rambo"));

        //使用 argThat 验证方法是否按特定参数调用
        verify(mockedPersonService).foo(argThat(person -> "Leo".equals(person.getName())));
        verify(mockedPersonService, times(1)).foo(argThat(person -> "Rambo".equals(person.getName())));

        //验证不满足条件的调用
        verify(mockedPersonService, never()).foo(argThat(person -> "UU".equals(person.getName())));
    }
}
