package com.hawkeye.rabbitMq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(String str)
    {
        System.out.println("进入");
        amqpTemplate.convertAndSend("hello",str);
    }

    public void senGender(String str){
        System.out.println("进入");
        amqpTemplate.convertAndSend("gender",str);
    }
}
