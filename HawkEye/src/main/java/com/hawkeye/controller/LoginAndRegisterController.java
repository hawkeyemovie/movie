package com.hawkeye.controller;

import com.hawkeye.pojo.User;
import com.hawkeye.service.impl.UserServiceImpl;
import com.hawkeye.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 登录和注册控制器
 */
@RestController
@RequestMapping("/loginandregister")
public class LoginAndRegisterController {

    @Autowired
    UserServiceImpl userService;

    /**
     * 得到密码
     * @return
     */
    private User getUser(String phone){
        return userService.getUser(phone);
    }

    /**
     * 是否注册成功
     * @param phone 电话号码
     * @param password 密码
     * @return
     */
    private boolean isRegister(String phone,String password){
        return userService.addUser(phone,Md5.encryption(password));
    }

    /**
     * 登录
     * @param phone 电话号码
     * @param password 密码
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam("phone")String phone,@RequestParam("password")String password){
        User user = getUser(phone);
        if (user.getPassword().equals(Md5.encryption(password))){
            return "true";
        }
        return "false";
    }

    /**
     * 注册
     * @param phone 电话号码
     * @param password 密码
     * @return
     */
    @RequestMapping("/register")
    public String register(@RequestParam("phone")String phone,@RequestParam("password")String password){
        if (isRegister(phone,password)){
            return "true";
        }
        return "false";
    }


}
