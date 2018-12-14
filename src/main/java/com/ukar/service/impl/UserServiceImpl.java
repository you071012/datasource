package com.ukar.service.impl;

import com.ukar.annotation.DataSourceAnnotation;
import com.ukar.entity.User;
import com.ukar.enums.DataSourceEnum;
import com.ukar.mapper.UserMapper;
import com.ukar.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * Created by jyou on 2018/9/11.
 *
 * @author jyou
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
//    @DataSourceAnnotation(value = DataSourceEnum.Master)
    public List<User> selectAllUser() {
        return userMapper.selectAll();
    }

    @Override
    @DataSourceAnnotation(value = DataSourceEnum.Slave01)
    public List<User> selectAllUserFromSlave() {
        return userMapper.selectAll();
    }

    @Override
    public User findById(long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        try{
            Random random = new Random();
            int num = random.nextInt(10);
            System.out.println(num);
            user.setPassword(user.getPassword() + num);
            userMapper.updateByPrimaryKeySelective(user);
            int i = 1 / 0;
        }catch (Exception e){
            e.printStackTrace();
            //手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }
}
