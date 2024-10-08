package com.shawn;

import com.shawn.service.AppConfig;
import com.shawn.service.OrderService;
import com.shawn.service.UserInterface;
import com.shawn.service.UserService;
import com.shawn.spring.ShawnApplicationContext;

public class SimulatorApplication {
    public static void main(String[] args) throws Exception {
        ShawnApplicationContext applicationContext = new ShawnApplicationContext(AppConfig.class);
        UserInterface userService = (UserInterface) applicationContext.getBean("userService");
        System.out.println(userService);
        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
        System.out.println(orderService);
        userService.test();
    }
}
