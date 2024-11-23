package com.future.rocket.mockito.vision.suite;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockAnnotationTest {

    @Mock
    private ArticleCalculator calculator;

    @Mock
    private ArticleDatabase database;

    @Mock
    private UserProvider userProvider;

    @InjectMocks
    private ArticleManager articleManager;

    @Before
    public void setUp() {
        when(calculator.calculateTax(100.00)).thenReturn(30.0);
        when(userProvider.getUserName()).thenReturn("Foo");
    }

    @Test
    public void testCalculateTax() {
        double result = articleManager.calculateTax(100.00);

        verify(calculator, times(1)).calculateTax(100.00);
        System.out.println("Calculated tax: " + result);
    }

    @Test
    public void testGetUserName() {
        String userName = articleManager.getUserName();
        verify(userProvider).getUserName();
        System.out.println("UserName: " + userName);

    }

    static class ArticleManager {
        private final ArticleCalculator calculator;
        private final ArticleDatabase database;
        private final UserProvider userProvider;

        public ArticleManager(ArticleCalculator calculator, ArticleDatabase database, UserProvider userProvider) {
            this.calculator = calculator;
            this.database = database;
            this.userProvider = userProvider;
        }

        public double calculateTax(double value) {
            calculator.calculateTax(value);
            return calculator.calculateTax(value);
        }

        public String getUserName() {
            return userProvider.getUserName();
        }
    }

    class ArticleCalculator {
        public double calculateTax(double value) {
            return value * 0.2;
        }
    }

    class ArticleDatabase {
        public void saveArticle(String article) {
            System.out.println("Saving article ==> " + article);
        }
    }

    class UserProvider {
        public String getUserName() {
            return "Leo";
        }
    }
}
