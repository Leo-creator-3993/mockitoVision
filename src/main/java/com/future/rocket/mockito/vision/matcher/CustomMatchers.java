package com.future.rocket.mockito.vision.matcher;

import org.mockito.ArgumentMatcher;

public class CustomMatchers {

    public static class EvenNumberMatcher implements ArgumentMatcher<Integer> {
        @Override
        public boolean matches(Integer integer) {
            return (integer % 2 == 0);
        }
    }

    public static EvenNumberMatcher getEvenNumberMatcher() {
        return new EvenNumberMatcher();
    }

        public static class NameMatcher implements  ArgumentMatcher<String> {
            @Override
            public boolean matches(String s) {
                return s.contains("leo");
            }
        }

        public static NameMatcher getNameMather() {
            return new NameMatcher();
        }
}
