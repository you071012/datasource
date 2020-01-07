package com.ukar.service;

import com.ukar.entity.User;

import java.util.List;
import java.util.Map;

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
     *
     * @param user
     */
    void updateUser(User user);

    String mockTest();

    void insert(User user);

    /**
     * 批量插入
     *
     * @param list
     */
    void batchInsert(List<User> list);

    void tr(User user);

    Map<String, List<User>> selectMap();

    int masterInster(User user);

    int slave01Inster(User user);
}
