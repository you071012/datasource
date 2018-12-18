package com.ukar.service;

import com.ukar.DatasourceApplicationTests;
import com.ukar.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest(classes = DatasourceApplicationTests.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testUpdate(){
        User user = userService.findById(1);
        System.out.println(user);
        userService.updateUser(user);
        User user2 = userService.findById(1);
        System.out.println(user2);
    }

    @Test
    public void testFindUser(){
        for(int i = 0 ; i < 5 ; i++){
            User user = userService.findById(1);
            System.out.println(user);
        }
    }
}
