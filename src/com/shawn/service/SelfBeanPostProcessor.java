package com.shawn.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.shawn.spring.BeanPostProcessor;
import com.shawn.spring.Component;

@Component
public class SelfBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(String beanName, Object o) {
        if (beanName.equals("userService")) {
            System.out.println("postProcessBeforeInitialization: userService");
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object o) {
        if (beanName.equals("orderService")) {
            System.out.println("postProcessAfterInitialization: orderService");
        }

        if (beanName.equals("userService")) {
            Object proxyInstance = Proxy.newProxyInstance(SelfBeanPostProcessor.class.getClassLoader(), o.getClass().getInterfaces() , new InvocationHandler() {
            
                            @Override
                            public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
                                System.out.println("代理逻辑"); 
                                return arg1.invoke(o, arg2);
                            }
                            
                        });
            return proxyInstance;
        }

        return o;
    }

}
