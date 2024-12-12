package com.future.rocket.mockito.vision.provider;

import org.mockito.creation.instance.Instantiator;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.InstantiatorProvider2;

public class CustomInstantiatorProvider implements InstantiatorProvider2 {

    public CustomInstantiatorProvider() {
        System.out.println("CustomInstantiatorProvider loaded");
    }


    @Override
    public Instantiator getInstantiator(MockCreationSettings<?> settings) {
        return new CustomInstantiator();
    }

    static class CustomInstantiator implements Instantiator {
        @Override
        public <T> T newInstance(Class<T> cls) {
            System.out.println("CustomInstantiator is being used to instantiate: " + cls.getName());
            try {
                //使用无参构造函数创建实例
                return cls.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.out.println("Error happened, e ==> " + e.getMessage());
            }
            return null;
        }
    }
}
