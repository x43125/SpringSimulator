package com.shawn.service;

import com.shawn.spring.Autowired;
import com.shawn.spring.BeanNameAware;
import com.shawn.spring.Component;
import com.shawn.spring.InitializingBean;

@Component
public class UserService implements BeanNameAware, InitializingBean {

    private String beanName;

    @Autowired
    private OrderService orderService;

    public void test() {
        System.out.println("测试autowired: " + orderService);
        System.out.println("beanName: " + beanName);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet---");

    }
    
}
