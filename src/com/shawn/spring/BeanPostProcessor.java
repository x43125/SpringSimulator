package com.shawn.spring;

public interface BeanPostProcessor {

    void postProcessBeforeInitialization(String beanName, Object o);

    void postProcessAfterInitialization(String beanName, Object o);
}
