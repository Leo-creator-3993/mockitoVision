package com.future.rocket.mockito.vision.suite;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.listeners.VerificationStartedListener;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.mockito.Mockito.verify;

public class VerificationStartedListenerTest_Index42 {

    public interface UserService {
        void doSomething();
    }

    @Test
    public void testProxyWithVerificationStartedListener() {
        // Step 1: 创建 Mock 对象
        UserService mockUserService = Mockito.mock(UserService.class);

        // Step 2: 模拟框架的代理对象
        UserService proxyUserService = createProxy(mockUserService);

        // Step 3: 创建 VerificationStartedListener
        VerificationStartedListener listener = event -> {
            // 替换为真实的 mock 对象
            if (event.getMock() == proxyUserService) {
                event.setMock(mockUserService);
            }
        };

        // Step 4: 将 Listener 注册到 MockSettings
        Mockito.mock(UserService.class, Mockito.withSettings().verificationStartedListeners(listener));

        // Step 5: 调用代理对象的方法
        proxyUserService.doSomething();

        // 验证代理对象调用是否被正确转发到真实的 mock 对象
        verify(mockUserService).doSomething();
    }

    // 模拟框架动态代理的创建
    private UserService createProxy(UserService target) {
        return (UserService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[]{UserService.class},
                new DynamicProxyHandler(target));
    }

    // 动态代理的处理器
    static class DynamicProxyHandler implements InvocationHandler {
        private final Object target;

        public DynamicProxyHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(target, args);
        }
    }
}
