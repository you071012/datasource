package com.ukar.service;

import com.ukar.DatasourceApplicationTests;
import com.ukar.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest(classes = DatasourceApplicationTests.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testUpdate() {
        User user = userService.findById(1);
        System.out.println(user);
        userService.updateUser(user);
        User user2 = userService.findById(1);
        System.out.println(user2);
    }

    @Test
    public void testFindUser() {

//        System.out.println(userService.findById(1));
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            userService.findById(1);
//            System.out.println(userService.findById(1));
        }
        long end = System.currentTimeMillis();
        System.out.println("共消耗时间毫秒值time=" + (end - start));
    }

    @Test
    public void testInsert() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            User user = new User();
            user.setName("test" + i);
            user.setPassword("123");
            userService.insert(user);
        }
        long end = System.currentTimeMillis();
        System.out.println("共消耗时间毫秒值time=" + (end - start));

    }

    @Test
    public void testBatchInsert() {
        List<User> list = new ArrayList<>();
        String str = "";
        for (int i = 0; i < 200; i++) {
            str += "你";
        }
        for (int i = 0; i < 1000000; i++) {
            User user = new User();
            user.setName("batch" + str + i);
            user.setPassword("password" + str + i);
            list.add(user);
        }

        userService.batchInsert(list);
    }

    private void run(List<User> list) {
        Thread thread = new Thread(() -> userService.batchInsert(list));
        thread.start();
    }

    @Test
    public void testSelectMap() {
        Map<String, List<User>> map = userService.selectMap();
        System.out.println(map.size());


    }

}
