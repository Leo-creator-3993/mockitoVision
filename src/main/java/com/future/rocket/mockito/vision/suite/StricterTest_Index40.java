package com.future.rocket.mockito.vision.suite;

import com.future.rocket.mockito.vision.model.User;
import com.future.rocket.mockito.vision.repository.UserRepository;
import com.future.rocket.mockito.vision.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class StricterTest_Index40 {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserFullName() {
        User mockUser = new User("Leo", "X");
        when(userRepository.findById(1L)).thenReturn(mockUser);

        System.out.println("#1 FullName ==> " + userService.getUserFullName(1L));
        System.out.println("#2 FullName ==> " + userService.getUserFullName(2L));
    }

//    @Test
//    public void testGetUserFullNameWithStrictMocking() {
//        try {
//            // 这里没有为 userRepository.findById 设置任何存根行为
//            // Mockito 在严格模式下会抛出 MissingMethodInvocationException 异常
//            userService.getUserFullName(1L);  // 这行应该抛出异常
//            fail("Expected exception MissingMethodInvocationException was not thrown");
//        } catch (org.mockito.exceptions.misusing.MissingMethodInvocationException e) {
//            // 期望的异常被抛出
//            System.out.println("Caught expected exception: " + e.getClass().getName());
//        }
//    }

//    @Test(expected = org.mockito.exceptions.misusing.MissingMethodInvocationException.class)
//    public void testGetUserFullNameWithStrictMocking() {
//        userService.getUserFullName(1L);
//    }

//    @Test(expected = org.mockito.exceptions.misusing.MissingMethodInvocationException.class)
//    public void testGetUserFullNameWithStrictMocking() {
//        // 这里没有为 userRepository.findById() 设置存根行为
//        // Mockito 会抛出 MissingMethodInvocationException 异常
//        userService.getUserFullName(1L);
//    }


//    @Test
//    public void testGetUserFullNameWithStrictMocking() {
//        try {
//            // 没有为 userRepository.findById() 设置任何存根行为，应该抛出 MissingMethodInvocationException 异常
//            //userService.getUserFullName(1L);
//            userRepository.findById(1L);
//            // 如果代码执行到这里，则说明没有抛出预期异常，测试失败
//            fail("Expected exception MissingMethodInvocationException was not thrown");
//        } catch (org.mockito.exceptions.misusing.MissingMethodInvocationException e) {
//            // 处理期望的异常
//            System.out.println("Caught expected exception: " + e.getClass().getName());
//        }
//    }

//    @Test
//    public void testGetUserFullNameWithStrictMocking() {
//        try {
//            // 此处没有为 userRepository.findById() 设置存根行为，应该抛出 MissingMethodInvocationException 异常
//            userService.getUserFullName(1L);  // 这行代码会抛出异常
//            fail("Expected exception MissingMethodInvocationException was not thrown");
//        } catch (org.mockito.exceptions.misusing.MissingMethodInvocationException e) {
//            // 期望的异常被抛出
//            System.out.println("Caught expected exception: " + e.getClass().getName());
//        }
//    }

//    @Test
//    public void testGetUserFullNameWithStrictMocking() {
//        try {
//            // 清除之前的存根行为
//            Mockito.reset(userRepository);
//
//            // 此处没有为 userRepository.findById() 设置任何存根行为
//            // Mockito 应该抛出 MissingMethodInvocationException 异常
//            userService.getUserFullName(1L);  // 这行代码应该抛出异常
//            fail("Expected exception MissingMethodInvocationException was not thrown");
//        } catch (org.mockito.exceptions.misusing.MissingMethodInvocationException e) {
//            // 期望的异常被抛出
//            System.out.println("Caught expected exception: " + e.getClass().getName());
//        }
//    }
}
