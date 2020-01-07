package com.ukar.service.impl;

import com.ukar.entity.User;
import com.ukar.service.TxService;
import com.ukar.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author jia.you
 * @date 2020/01/07
 */
@Service
public class TxServiceImpl implements TxService {

    @Resource
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUserTxTest() {
        User user = new User();
        user.setName("ukar" + new Random().nextInt(1000));
        user.setPassword("111");

//        userService.masterInster(user);
//        System.out.println(111);
//        userService.slave01Inster(user);

    }
}
