package com.shawn.service;

import com.shawn.spring.BeanPostProcessor;
import com.shawn.spring.Component;

@Component
public class SelfBeanPostProcessor implements BeanPostProcessor {

    @Override
    public void postProcessBeforeInitialization(String beanName, Object o) {
        if (beanName.equals("userService")) {
            System.out.println("postProcessBeforeInitialization: userService");
        }
    }

    @Override
    public void postProcessAfterInitialization(String beanName, Object o) {
        if (beanName.equals("orderService")) {
            System.out.println("postProcessAfterInitialization: orderService");
        }
    }

}
