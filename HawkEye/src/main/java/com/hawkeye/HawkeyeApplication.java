package com.hawkeye;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.hawkeye.mapper")
@SpringBootApplication
@EnableCaching//redis缓存注解
public class HawkeyeApplication {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(HawkeyeApplication.class, args);
    }
}
