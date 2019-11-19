package com.hawkeye;

import com.alibaba.fastjson.JSON;
import com.hawkeye.pojo.MovieTicket;
import com.hawkeye.pojo.OrderSeat;
import com.hawkeye.pojo.Seat;
import com.hawkeye.rabbitMq.MsgProducer;
import com.hawkeye.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ss {
    @Autowired
    MovieTicket movieTicket;

    @Autowired
    List<OrderSeat> list;

    @Autowired
    private MsgProducer sender;

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void sendTest() throws Exception {
//        Date date  = new Date();

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(dateFormat.format(date.getTime()));
//        System.out.println(dateFormat.format(date.getTime() + 10 * 60 * 1000));
//        Map<String,List<String >> map = new HashMap<>();
//        List<String> list = new ArrayList<>();
//        list.add("");
//        list.add("");
//        list.add("");
//        list.add("");
        redisUtil.set("18529515196","123456");
        redisUtil.set("28529515196","123456");
        redisUtil.set("38529515196","123456");
        redisUtil.set("48529515196","123456");
        redisUtil.set("58529515196","123456");
//        Set set = redisUtil.keys("or");
//
//        for (Object o:set) {
//            System.out.println(o.toString());
//        }
    }

    @Test
    public void te() throws UnsupportedEncodingException {

        Calendar calendar = Calendar.getInstance();
        MovieTicket movieTicket = new MovieTicket();
        List<OrderSeat> list = new ArrayList<>();
        movieTicket.setUId(1);
        movieTicket.setChipFormationId(1);
        movieTicket.setCinemaId(1);
        movieTicket.setMovieId(1);
        movieTicket.setMovieHallId(1);

        String order = movieTicket.getUId().toString()+""
                +calendar.get(calendar.YEAR)
                +(calendar.get(Calendar.MONTH)+1)
                +calendar.get(Calendar.DATE)
                +movieTicket.getCinemaId().toString()
                +movieTicket.getMovieHallId().toString()
                +movieTicket.getMovieId().toString();

        list.add(new OrderSeat(null,1,null,new Seat("1排1座")));
        list.add(new OrderSeat(null,1,null,new Seat("1排2座")));

        movieTicket.setOrderSeatList(list);

        System.out.println(JSON.toJSONString(movieTicket));

    }




}
