package com.hawkeye.quartz.job;

import com.alibaba.fastjson.JSONObject;
import com.hawkeye.pojo.MovieTicket;
import com.hawkeye.rabbitMq.MsgProducer;
import com.hawkeye.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Set;

@Configuration
@EnableScheduling//开启任务调度
public class HelloJob {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    MsgProducer msgProducer;

    @Scheduled(cron = "0/10 * * * * ?")
    public void run(){
        Set set = redisUtil.keys("or");

        for (Object o:set) {

            JSONObject jsonObject  = JSONObject.parseObject(redisUtil.get(o.toString()));
            MovieTicket movieTicket = JSONObject.toJavaObject(jsonObject, MovieTicket.class);

            if (movieTicket.getFoundTime().getTime() + 10 * 60 * 1000 < new Date().getTime()){
                msgProducer.sendMsgC(redisUtil.get(o.toString()));
                System.out.println("检测到订单超时回执操作执行=============>>");
            }

            System.out.println(o.toString());
        }
    }
}
