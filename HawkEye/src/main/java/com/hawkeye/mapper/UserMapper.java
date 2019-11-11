package com.hawkeye.mapper;

import com.hawkeye.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 登录
     * @return
     */
    User getUser(@Param("phone")String phone);


    /**
     * 注册用户
     * @param phone 手机号
     * @param password 密文
     * @return
     */
    Integer addUser(@Param("phone")String phone,@Param("password")String password);
}
