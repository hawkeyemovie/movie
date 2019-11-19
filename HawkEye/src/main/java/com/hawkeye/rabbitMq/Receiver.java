package com.hawkeye.rabbitMq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hawkeye.pojo.MovieTicket;
import com.hawkeye.pojo.OrderSeat;
import com.hawkeye.service.CinemaHallService;
import com.hawkeye.service.MovieTicketService;
import com.hawkeye.service.OrderSeatService;
import com.hawkeye.service.SeatService;
import com.hawkeye.util.OrderUtil;
import com.hawkeye.util.RedisUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class Receiver {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    MovieTicketService movieTicketService;

    @Autowired
    OrderSeatService orderSeatService;

    @Autowired
    SeatService seatService;

    @Autowired
    CinemaHallService cinemaHallService;



    /**
     * UPDATE `cinema_hall` SET `layout` = WHERE `id` =
     * 付款
     * @param str
     */
    @RabbitHandler
    @RabbitListener(queues = "hello")
    public void process(String  str) {
//        JSONObject jsonObject  = JSONObject.parseObject(str);
//        MovieTicket movieTicket = JSONObject.toJavaObject(jsonObject, MovieTicket.class);
//
//        for (OrderSeat orderSeat:movieTicket.getOrderSeatList()) {
//
//            //添加座位
//            System.out.println("座位编号======>"+seatService.addSeat(movieTicket.getMovieHallId(), orderSeat.getSeat().getSeatName(), OrderUtil.getRow(orderSeat.getSeat().getSeatName()), OrderUtil.getColumn(orderSeat.getSeat().getSeatName())));
//
//            //添加订单座位
//            orderSeatService.addOrderSeat(movieTicket.getOrder(),seatService.getMaxSid());
//        }
//
//        //添加订单
//        movieTicketService.addTicket(movieTicket.getOrder(),movieTicket.getTicketCode(),movieTicket.getQrcodeRoute(),movieTicket.getMovieId(),movieTicket.getCinemaId(),movieTicket.getMovieHallId(),movieTicket.getChipFormationId(),movieTicket.getTicketPrice(),1,movieTicket.getFoundTime(),movieTicket.getUId());
//
//        //修改布局
//        cinemaHallService.upCinemaHall(movieTicket.getChipFormation().getCinemaHall().getLayout(),movieTicket.getMovieHallId());
//
//        System.out.println("接收消息：" + str);
//        System.out.println("接收消息时间：" + new Date());
    }

    /**
     *处理订单
     * =====》生成座位唯一标识码
     * 根据用户id 影院id 排片id（通过排片id 获取电影id 影厅id）电影id 影厅id 当前日期 座位id
     * 通过该标识判断该座位是否已被下单
     * 是：若已存在，直接返回，不在进行下一步操作
     * 否：存入redis中
     *
     * =====》生成订单号
     * 通过验证座位唯一标识码
     * 确定用户选择的座位可以下单后
     * 根据根据用户id,影院id,排片id（通过排片id 获取电影id 影厅id 播放日期）电影id 影厅id 当前日期
     * 生成订单号
     * 订单号为键，MovieTicket对象转为json做值存入redis
     * @param str
     */
    @RabbitHandler
    @RabbitListener(queues = "gender")
    public void getGender(String  str) {
//        try {
//            JSONObject jsonObject  = JSONObject.parseObject(str);
//            MovieTicket movieTicket = JSONObject.toJavaObject(jsonObject, MovieTicket.class);//得到订单对象
//
//            List<OrderSeat> list = movieTicket.getOrderSeatList();//得到订单座位集合，座位至少有一个
//
//            Calendar calendar = Calendar.getInstance();
//
//            String seat = "";
//
//            //遍历座位生成座位唯一标识
//            for (OrderSeat orderSeat:list) {
//                seat = calendar.get(calendar.YEAR)+""
//                        +(calendar.get(Calendar.MONTH)+1)
//                        +calendar.get(Calendar.DATE)
//                        +movieTicket.getCinemaId().toString()
//                        +movieTicket.getMovieHallId().toString()
//                        +movieTicket.getChipFormationId()
//                        +movieTicket.getMovieId().toString()
//                        +OrderUtil.extract(orderSeat.getSeat().getSeatName());
//                //判断该座位是否已被购买
//                if (redisUtil.hasKey(seat)){
//                    System.out.println("键已存在！处理退出！");
//                    return;
//                }
//            }
//
//            //通过验证
//            for (OrderSeat orderSeat:list) {
//                seat = calendar.get(calendar.YEAR)+""
//                        +(calendar.get(Calendar.MONTH)+1)
//                        +calendar.get(Calendar.DATE)
//                        +movieTicket.getCinemaId()
//                        +movieTicket.getMovieHallId()
//                        +movieTicket.getChipFormationId()
//                        +movieTicket.getMovieId().toString()
//                        +OrderUtil.extract(orderSeat.getSeat().getSeatName());
//                System.out.println("座位唯一号=============>"+seat);
//                //将座位唯一标识存入redis，键：标识    值：标识
//                redisUtil.set(seat,seat);
//            }
//
//            //按照订单生成规则生成订单号
//            String order = movieTicket.getUId().toString()+""
//                    +calendar.get(calendar.YEAR)
//                    +(calendar.get(Calendar.MONTH)+1)
//                    +calendar.get(Calendar.DATE)
//                    +calendar.get(Calendar.HOUR_OF_DAY)
//                    +calendar.get(Calendar.MINUTE)
//                    +movieTicket.getCinemaId()
//                    +movieTicket.getMovieHallId()
//                    +movieTicket.getChipFormationId()
//                    +movieTicket.getMovieId();
//
//            System.out.println("订单号=============>"+order);
//
//            //设置订单号
//            movieTicket.setOrder(order);
//
//            //修改布局
//            String layoutNum = ""+calendar.get(calendar.YEAR)
//                    +(calendar.get(Calendar.MONTH)+1)
//                    +calendar.get(Calendar.DATE)
//                    +movieTicket.getCinemaId()
//                    +movieTicket.getMovieHallId()
//                    +movieTicket.getChipFormationId();
//
//            System.out.println("影厅布局号=============>"+layoutNum);
//
//            String layout = movieTicket.getChipFormation().getCinemaHall().getLayout();
//
//            for (OrderSeat orderSeat:list) {
//                layout = OrderUtil.modifyLayout(layout,orderSeat.getSeat().getSeatName(),movieTicket.getChipFormation().getCinemaHall().getColumn());
//            }
//
//            //布局号
//            redisUtil.set(layoutNum,layout);
//
//            //设置布局
//            movieTicket.getChipFormation().getCinemaHall().setLayout(layout);
//
//            //设置时间
//            movieTicket.setFoundTime(new Date());
//
//            //将订单号存入redis键：订单号   值：订单对象 下单成功
//            redisUtil.set(movieTicket.getOrder(), JSON.toJSONString(movieTicket));
//
//        } catch (Exception e) {
//            System.out.println("参数解析异常！处理退出！"+e);
//        }finally {
//            System.out.println("接收消息：" + str);
//            System.out.println("接收消息时间：" + new Date());
//        }
    }
}
