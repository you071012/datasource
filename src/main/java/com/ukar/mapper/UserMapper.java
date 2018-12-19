package com.ukar.mapper;

import com.ukar.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by jyou on 2017/9/15.
 */
public interface UserMapper extends Mapper<User> {
    User selectByName(@Param("name") String name);

    String mockTest();
}
