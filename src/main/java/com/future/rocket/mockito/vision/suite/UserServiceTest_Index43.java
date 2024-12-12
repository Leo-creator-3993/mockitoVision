package com.future.rocket.mockito.vision.suite;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.quality.Strictness;
import org.mockito.session.MockitoSessionLogger;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest_Index43 {

    //模拟存储库
    @Mock
    private UserRepository userRepository;

    //模拟通知服务
    @Mock
    private NotificationService notificationService;

    //待测试的服务
    private UserService userService;

    @Test
    public void testWithMockitoSession() {
        //创建自定义日志器
        MockitoSessionLogger customLogger = hint ->
                System.out.println("Custom Log: " + hint);

        //构建 MockitoSession
        MockitoSession session = Mockito.mockitoSession()
                .name("FooFoo")      //设置会话名称
                .logger(customLogger)                //自定义日志器
                .initMocks(this)                     //初始化当前类中的 @Mock 字段
                .strictness(Strictness.STRICT_STUBS) //设置初始严格性
                .startMocking();

        Throwable testFailure = null;
        try {
            //配置存储库mock行为
            when(userRepository.findUserById(1L)).thenReturn(new User(1L, "Alice"));

            //初始化待测试服务
            userService = new UserService(userRepository, notificationService);

            //调用服务方法
            userService.processUser(1L);

            //验证存储库调用
            verify(userRepository).findUserById(1L);

            //验证通知服务调用
            verify(notificationService).sendNotification("User processed: Alice");

        } catch (Throwable t) {
            testFailure = t; //捕获测试失败
        } finally {
            session.finishMocking(testFailure); //结束会话并处理错误
        }
    }

    //用户服务类
    public static class UserService {
        private final UserRepository userRepository;
        private final NotificationService notificationService;

        public UserService(UserRepository userRepository, NotificationService notificationService) {
            this.userRepository = userRepository;
            this.notificationService = notificationService;
        }

        public void processUser(Long id) {
            User user = userRepository.findUserById(id);
            if (user != null) {
                System.out.println("Processing user: " + user.getName());
                notificationService.sendNotification("User processed: " + user.getName());
            }
        }
    }

    //用户存储库接口
    public interface UserRepository {
        User findUserById(Long id);
    }

    //通知服务接口
    public interface NotificationService {
        void sendNotification(String message);
    }

    //用户实体类
    public static class User {
        private final Long id;
        private final String name;

        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
