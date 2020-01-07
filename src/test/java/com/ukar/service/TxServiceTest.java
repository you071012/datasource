package com.ukar.service;

import com.ukar.DatasourceApplicationTests;
import com.ukar.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest(classes = DatasourceApplicationTests.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TxServiceTest {

    @Resource
    private TxService txService;

    @Resource
    private UserService userService;

    @Test
    public void test() {
        txService.insertUserTxTest();

//        User user = new User();
//        user.setName("ukar" + new Random().nextInt(1000));
//        user.setPassword("111");
//        userService.slave01Inster(user);
    }

}
