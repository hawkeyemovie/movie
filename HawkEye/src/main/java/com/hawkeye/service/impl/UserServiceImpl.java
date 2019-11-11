package com.hawkeye.service.impl;

import com.hawkeye.mapper.UserMapper;
import com.hawkeye.pojo.User;
import com.hawkeye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 获得用户
     * @param phone 电话号码
     * @return
     */
    @Override
    public User getUser(String phone) {
        return userMapper.getUser(phone);
    }

    @Override
    public boolean addUser(String phone, String password) {
        return 1==userMapper.addUser(phone,password);
    }
}
