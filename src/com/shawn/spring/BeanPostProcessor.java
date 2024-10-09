package com.shawn.spring;

public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(String beanName, Object o);

    Object  postProcessAfterInitialization(String beanName, Object o);
}
