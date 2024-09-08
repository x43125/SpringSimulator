package com.shawn.service;

import com.shawn.spring.Autowired;
import com.shawn.spring.Component;

@Component
public class UserService {

    @Autowired
    private OrderService orderService;

    public void test() {
        System.out.println("测试autowired: " + orderService);
    }
    
}
