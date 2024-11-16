package com.future.rocket.mockito.vision;

import com.future.rocket.mockito.vision.iface.MyService;
import com.future.rocket.mockito.vision.matcher.CustomMatchers;
import com.future.rocket.mockito.vision.model.Person;
import com.future.rocket.mockito.vision.service.PersonService;
import com.future.rocket.mockito.vision.utils.OtherTool;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoTestMain {

    @Test
    public void test0() {
        System.out.println("hi, welcome to mockito house, we will launch mockito rocket now, seat well please!");
    }

    @Test
    public void testBehaviour() {
        //创建mock对象
        List<String> mockedList = mock(List.class);

        //使用mock对象
        mockedList.add("one"); //并不是一个真正的List的行为,所以before 也会返回null
        System.out.println("before clear ==> " + mockedList.get(0));
        mockedList.clear();
        System.out.println("after clear ==> " + mockedList.get(0));

        //校验mock对象
        verify(mockedList).add("one");
        //verify(mockedList).add("two"); //未被调用过,验证不通过
        verify(mockedList).clear();
        verify(mockedList, times(1)).add("one");
        verify(mockedList, never()).add("two");
    }

    @Test
    public void testStubbing() {
        //创建mock对象
        LinkedList<String> mockedList = mock(LinkedList.class);

        //存根(stubbing)
        when(mockedList.get(0)).thenReturn("leo");
        when(mockedList.get(1)).thenReturn("foo");

        //结果输出
        System.out.println("index0 ==> " + mockedList.get(0));
        System.out.println("index1 ==> " + mockedList.get(1));
        System.out.println("index999 ==> " + mockedList.get(999));

        //校验
        verify(mockedList).get(0);
        verify(mockedList).get(1);
        //verify(mockedList, never()).get(999);//被调用了,但是没被存根
    }

    @Test
    public void testArgumentMatchers() {
        List<Integer> mockedList = mock(ArrayList.class);
        when(mockedList.contains(argThat(CustomMatchers.getEvenNumberMatcher()))).thenReturn(true);
        System.out.println("x ==> " + mockedList.contains(2));
        System.out.println("u ==> " + mockedList.contains(5));
        OtherTool.printSplitLine();
        when(mockedList.get(anyInt())).thenReturn(1);
        System.out.println("m ==> " + mockedList.get(8));
    }

    @Test
    public void testArgumentMatchers1() {
        List<Integer> mockedList = mock(ArrayList.class);
        when(mockedList.contains(argThat(CustomMatchers.getNameMather()))).thenReturn(true);
        System.out.println("leo ==> " + mockedList.contains("leo"));
        System.out.println("foo ==> " + mockedList.contains("foo"));
    }


    @Test
    public void testArgumentMatchers2() {
        //创建一个MyService的mock对象
        MyService myServiceMock = mock(MyService.class);

        //使用argThat进行匹配,并配置返回值为String
        when(myServiceMock.processInput(argThat(input -> input != null && input.startsWith("valid"))))
                .thenReturn("Processed Successfully");

        //测试用例:匹配成功
        System.out.println("Result: " + myServiceMock.processInput("validInput"));  // 输出 "Processed Successfully"

        //测试用例:匹配失败
        System.out.println("Result: " + myServiceMock.processInput("invalidInput"));  // 输出 null
    }

    @Test
    public void testArgumentMatchers3() {
        PersonService personServiceMock = mock(PersonService.class);
        when(personServiceMock.findPerson(argThat(name -> name.equals("leo")))).thenReturn(new Person("leo", 18));
        System.out.println("r1 ==> " + personServiceMock.findPerson("leo"));
        System.out.println("r2 ==> " + personServiceMock.findPerson("foo"));
    }

    @Test
    public void testArgumentMatchers4() {
        //创建一个真实的 PersonService 实例并预置数据
        PersonService personService = new PersonService();
        personService.add(new Person("leo", 18));
        personService.add(new Person("alice", 25));
        personService.add(new Person("bob", 30));
        personService.add(new Person("charlie", 22));

        //创建一个 mock 对象并部分模拟行为
        PersonService personServiceMock = mock(PersonService.class);
        //使用 argThat 进行参数匹配
        when(personServiceMock.findPerson(argThat(name -> name != null && name.startsWith("leo"))))
                .thenAnswer(invocation -> personService.findPerson(invocation.getArgument(0)));

        //测试：匹配成功时，返回预置的数据
        System.out.println("Result for 'leo': " + personServiceMock.findPerson("leo"));  // 期望输出预置的 Person{name='leo', age=18}

        //测试：匹配失败时，返回 null
        System.out.println("Result for 'foo': " + personServiceMock.findPerson("foo"));  // 期望输出 null
    }

    @Test
    public void testVerifying() {

        List<String> mockedList = mock(List.class);

        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        verify(mockedList, never()).add("foo");

        verify(mockedList, atMostOnce()).add("once");
        verify(mockedList, atLeastOnce()).add("twice");
        verify(mockedList, atLeast(2)).add("twice");
        verify(mockedList, atMost(3)).add("three times");
    }

    @Test(expected = RuntimeException.class)
    public void testVoidMethodThrowsException() {
        List<String> mockedList = mock(List.class);

        doThrow(new RuntimeException("Clear method failed")).when(mockedList).clear();

        mockedList.clear();
    }

    @Test
    public void testVoidMethodExceptionHandling() {
        List<String> mockedList = mock(List.class);
        doThrow(new RuntimeException("Leo foo foo")).when(mockedList).clear();

        try {
            mockedList.clear();
        } catch (RuntimeException e) {
            System.out.println("catch exception ==> " + e.getMessage());
        }
    }

    @Test
    public void testSingleOrder() {
        List singleMock = mock(List.class);

        singleMock.add("foo");
        singleMock.add("leo");

        InOrder inOrder = inOrder(singleMock);

        inOrder.verify(singleMock).add("foo");
        inOrder.verify(singleMock).add("leo");
    }

    @Test
    public void testMultiOrder() {
        List leoMockedList = mock(List.class);
        List fooMockedList = mock(List.class);

        leoMockedList.add("leo");
        fooMockedList.add("foo");

        InOrder inOrder = inOrder(fooMockedList, leoMockedList);

        inOrder.verify(leoMockedList).add("leo");
        inOrder.verify(fooMockedList).add("foo");
    }

    @Test
    public void testMockInteractions() {
        List<String> mockOne = mock(List.class);
        List<String> mockLeo = mock(List.class);
        List<String> mockFoo = mock(List.class);

        mockOne.add("one");

        verify(mockOne, times(1)).add("one");
        verify(mockLeo, never()).add("leo");
        verify(mockFoo, atMost(0)).add("foo");

        verifyNoInteractions(mockFoo, mockLeo);
    }

    @Test
    public void testRedundantInvocations() {
        List<String> mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");
        verify(mockedList).add("two");
        verifyNoMoreInteractions(mockedList);
    }


}
