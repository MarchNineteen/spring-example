package com.wyb.test.spring.aop;

import com.wyb.test.spring.HelloService;
import com.wyb.test.spring.HelloServiceImpl;
import com.wyb.test.spring.smallSpring.aop.aspectj.AspectJExpressionPointcut;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AspectJExpressionPointcutTest {

    @Test
    public void testClassFilter() throws Exception {
        String expression = "execution(* com.wyb.test.spring.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.matchers(HelloService.class);
        assertTrue(matches);
    }

    @Test
    public void testMethodMatcher() throws Exception {
        String expression = "execution(* com.wyb.test.spring.*.sayHelloWorld(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.matchers(
                HelloServiceImpl.class.getDeclaredMethod("sayHelloWorld"), HelloServiceImpl.class);
        assertTrue(matches);
    }
}
