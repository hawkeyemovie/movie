package com.hawkeye;

import com.hawkeye.pojo.ChipFormation;
import com.hawkeye.pojo.MovieTicket;
import com.hawkeye.pojo.OrderSeat;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@MapperScan("com.hawkeye.mapper")
@SpringBootApplication
@EnableCaching//redis缓存注解
@EnableRabbit//Rabbit队列注解
public class HawkeyeApplication {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(HawkeyeApplication.class, args);
    }

    @Bean
    public MovieTicket movieTicket(){
        return new MovieTicket();
    }

    @Bean
    public List<OrderSeat> list(){
        return new ArrayList<>();
    }

    @Bean
    public ChipFormation chipFormation(){return new ChipFormation();}
}

