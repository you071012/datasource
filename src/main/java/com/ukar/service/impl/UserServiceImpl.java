package com.ukar.service.impl;

import com.ukar.annotation.DataSourceAnnotation;
import com.ukar.datasource.DynamicDataSource;
import com.ukar.entity.User;
import com.ukar.enums.DataSourceEnum;
import com.ukar.mapper.UserMapper;
import com.ukar.service.UserService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    @DataSourceAnnotation(value = DataSourceEnum.Master)
    public List<User> selectAllUser() {
        return userMapper.selectAll();
    }

    @Override
    @DataSourceAnnotation(value = DataSourceEnum.Slave)
    public List<User> selectAllUserFromSlave() {
        return userMapper.selectAll();
    }

    @Override
    @DataSourceAnnotation(value = DataSourceEnum.Master)
    public User findById(long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @DataSourceAnnotation(value = DataSourceEnum.Master)
    public void updateUser(User user) {

        try {
            Random random = new Random();
            int num = random.nextInt(10);
            System.out.println(num);
            user.setPassword(user.getPassword() + num);
            userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            e.printStackTrace();
            //手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }

    @Override
    public String mockTest() {
        return userMapper.mockTest();
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsert(List<User> list) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        // 每批commit的个数
        int batchCount = 4000;
        // 每批最后一个的下标
        int batchLastIndex = batchCount;
        for (int index = 0; index < list.size(); index++) {
            if (batchLastIndex >= list.size()) {
                batchLastIndex = list.size();
                sqlSession.insert("com.ukar.mapper.UserMapper.batchInsert", list.subList(index,
                        batchLastIndex));
                sqlSession.commit();
                break;// 数据插入完毕，退出循环
            } else {
                sqlSession.insert("com.ukar.mapper.UserMapper.batchInsert", list.subList(index, batchLastIndex));
                sqlSession.commit();
                // 设置下一批下标
                index = batchLastIndex;
                batchLastIndex = index + (batchCount - 1);
            }
        }
        sqlSession.commit();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tr(User user) {
        updateUser(user);
    }

    @Override
    public Map<String, List<User>> selectMap() {
        return userMapper.selectMap();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @DataSourceAnnotation(value = DataSourceEnum.Master)
    public int masterInster(User user) {
        return userMapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @DataSourceAnnotation(value = DataSourceEnum.Slave01)
    public int slave01Inster(User user) {
        return userMapper.insert(user);
    }
}
