package com.ukar.service;

import com.ukar.entity.User;

import java.util.List;

/**
 * Created by jyou on 2018/9/11.
 *
 * @author jyou
 */
public interface UserService {

    List<User> selectAllUser();

    List<User> selectAllUserFromSlave();

    User findById(long id);

    /**
     * 测试手动回滚
     * @param user
     */
    void updateUser(User user);
}
