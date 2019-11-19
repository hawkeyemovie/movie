package com.hawkeye.rabbitMq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hawkeye.config.RabbitConfig;
import com.hawkeye.pojo.MovieTicket;
import com.hawkeye.pojo.OrderSeat;
import com.hawkeye.service.CinemaHallService;
import com.hawkeye.service.MovieTicketService;
import com.hawkeye.service.OrderSeatService;
import com.hawkeye.service.SeatService;
import com.hawkeye.util.OrderUtil;
import com.hawkeye.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class MsgReceiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    public void processA(String content) {
        try {
            JSONObject jsonObject  = JSONObject.parseObject(content);
            MovieTicket movieTicket = JSONObject.toJavaObject(jsonObject, MovieTicket.class);//得到订单对象

            List<OrderSeat> list = movieTicket.getOrderSeatList();//得到订单座位集合，座位至少有一个

            Calendar calendar = Calendar.getInstance();

            String seat = "";
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
//                        + OrderUtil.extract(orderSeat.getSeat().getSeatName());
//                //判断该座位是否已被购买
//                if (redisUtil.hasKey(seat)){
//                    System.out.println("键已存在！处理退出！");
//                    return;
//                }
//            }

            //通过验证
            for (OrderSeat orderSeat:list) {
                seat = calendar.get(calendar.YEAR)+""
                        +(calendar.get(Calendar.MONTH)+1)
                        +calendar.get(Calendar.DATE)
                        +movieTicket.getCinemaId()
                        +movieTicket.getMovieHallId()
                        +movieTicket.getChipFormationId()
                        +movieTicket.getMovieId().toString()
                        +OrderUtil.extract(orderSeat.getSeat().getSeatName());
                System.out.println("座位唯一号=============>"+seat);
                //将座位唯一标识存入redis，键：标识    值：标识
                redisUtil.set(seat,seat);
            }

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

            //修改布局
            String layoutNum = "lo"+
                    movieTicket.getCinemaId()+""
                    +movieTicket.getMovieHallId();

            System.out.println("影厅布局号=============>"+layoutNum);

            String layout = movieTicket.getChipFormation().getCinemaHall().getLayout();

            for (OrderSeat orderSeat:list) {
                layout = OrderUtil.modifyLayout2(layout,orderSeat.getSeat().getSeatName(),movieTicket.getChipFormation().getCinemaHall().getColumn());
            }

            //设置布局
            movieTicket.getChipFormation().getCinemaHall().setLayout(OrderUtil.join(layout));

            //布局号 键：布局号  值：影厅对象
            redisUtil.set(layoutNum,JSON.toJSONString(movieTicket.getChipFormation().getCinemaHall()));

            //设置时间
            movieTicket.setFoundTime(new Date());

            //将订单号存入redis键：订单号   值：订单对象 下单成功
            redisUtil.set(movieTicket.getOrder(), JSON.toJSONString(movieTicket));
        } catch (Exception e) {
            System.out.println("参数解析异常！处理退出！"+e);
        }finally {
            System.out.println("接收消息：" + content);
            System.out.println("接收消息时间：" + new Date());
        }
    }



    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_B)
    @Transactional(propagation = Propagation.SUPPORTS)
    public void processB(String content) {
        JSONObject jsonObject  = JSONObject.parseObject(content);
        MovieTicket movieTicket = JSONObject.toJavaObject(jsonObject, MovieTicket.class);

        /**
         * 若已支付直接退出
         */

        try {
            for (OrderSeat orderSeat:movieTicket.getOrderSeatList()) {

                //添加座位
                System.out.println("座位编号======>"+seatService.addSeat(movieTicket.getMovieHallId(), orderSeat.getSeat().getSeatName(), OrderUtil.getRow(orderSeat.getSeat().getSeatName()), OrderUtil.getColumn(orderSeat.getSeat().getSeatName())));

                //添加订单座位
                orderSeatService.addOrderSeat(movieTicketService.getMaxId()+1,seatService.getMaxSid());
            }

            //添加订单
            movieTicketService.addTicket(movieTicket.getOrder(),movieTicket.getTicketCode(),movieTicket.getQrcodeRoute(),movieTicket.getMovieId(),movieTicket.getCinemaId(),movieTicket.getMovieHallId(),movieTicket.getChipFormationId(),movieTicket.getTicketPrice(),1,movieTicket.getFoundTime(),movieTicket.getUId());

            //修改布局
            cinemaHallService.upCinemaHall(movieTicket.getChipFormation().getCinemaHall().getLayout(),movieTicket.getMovieHallId());

            //修改缓存订单状态为已付款
            movieTicket.setPayState(1);

            //更新缓存
            redisUtil.set(movieTicket.getOrder(),JSON.toJSONString(movieTicket));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("接收消息：" + content);
            System.out.println("接收消息时间：" + new Date());
        }
    }


    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_C)
    public void processC(String content) {

        Calendar calendar = Calendar.getInstance();

        JSONObject jsonObject  = JSONObject.parseObject(content);
        MovieTicket movieTicket = JSONObject.toJavaObject(jsonObject, MovieTicket.class);

        List<OrderSeat> list = movieTicket.getOrderSeatList();

        //修改布局
        String layoutNum = "lo"+
                movieTicket.getCinemaId()+""
                +movieTicket.getMovieHallId();

        String seat = "";

        System.out.println("影厅布局号=============>"+layoutNum);

        String layout = OrderUtil.remove(movieTicket.getChipFormation().getCinemaHall().getLayout());
        System.out.println("影厅布局=============>"+layout);
        for (OrderSeat orderSeat:list) {
            seat = calendar.get(calendar.YEAR)+""
                    +(calendar.get(Calendar.MONTH)+1)
                    +calendar.get(Calendar.DATE)
                    +movieTicket.getCinemaId().toString()
                    +movieTicket.getMovieHallId().toString()
                    +movieTicket.getChipFormationId()
                    +movieTicket.getMovieId().toString()
                    + OrderUtil.extract(orderSeat.getSeat().getSeatName());
            redisUtil.del(seat);//取消已购买状态
            layout = OrderUtil.modifyLayout1(layout, orderSeat.getSeat().getSeatName(), movieTicket.getChipFormation().getCinemaHall().getColumn());

        }

        //设置布局
        movieTicket.getChipFormation().getCinemaHall().setLayout(OrderUtil.join(layout));

        //布局号 键：布局号  值：影厅对象
        redisUtil.set(layoutNum,JSON.toJSONString(movieTicket.getChipFormation().getCinemaHall()));

        redisUtil.del(movieTicket.getOrder());

        System.out.println("接收消息：" + content);
        System.out.println("接收消息时间：" + new Date());
    }
}
