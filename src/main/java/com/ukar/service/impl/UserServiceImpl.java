package com.ukar.service.impl;

import com.ukar.annotation.DataSourceAnnotation;
import com.ukar.entity.User;
import com.ukar.enums.DataSourceEnum;
import com.ukar.mapper.UserMapper;
import com.ukar.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
