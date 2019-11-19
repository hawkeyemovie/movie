package com.hawkeye.controller;

import com.hawkeye.rabbitMq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rm")
public class RmController {

    @Autowired
    private Sender sender;

    @RequestMapping("/hello")
    public String helloTest(String  str){
        sender.send(str);
        return "success";
    }
}
