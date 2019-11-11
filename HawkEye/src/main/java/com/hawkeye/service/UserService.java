package com.hawkeye.service;


import com.hawkeye.pojo.User;

public interface UserService {
    User getUser(String phone);
    boolean addUser(String phone,String password);
}
