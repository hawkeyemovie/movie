package com.hawkeye.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hawkeye.pojo.*;
import com.hawkeye.rabbitMq.MsgProducer;
import com.hawkeye.service.*;
import com.hawkeye.service.CinemaService;
import com.hawkeye.util.OrderUtil;
import com.hawkeye.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购票流程控制器
 */
@Controller
@RequestMapping("/shop")
public class ShoppingController {

    /**
     * 订单（电影票）
     */
    @Autowired
    MovieTicketService movieTicketService;

    /**
     * 订单业务集合
     */
    Map<String , MovieTicket> movieTicketMap = new HashMap();

    /**
     * 订单座位集合
     */
    @Autowired
    List<OrderSeat> list;

    @Autowired
    MovieTicket movieTicket;

    @Autowired
    ChipFormation chipFormation;

    /**
     * mq生产者
     */
    @Autowired
    private MsgProducer msgProducer;

    /**
     * redis操作工具类
     */
    @Autowired
    RedisUtil redisUtil;

    /**
     * 用户操作类
     */
    @Autowired
    UserService userService;

    /**
     * 电影操作类
     */
    @Autowired
    MovieService movieService;

    /**
     * 影院操作类
     */
    @Autowired
    CinemaService cinemaService;

    /**
     * 影厅操作类
     */
    @Autowired
    CinemaHallService cinemaHallService;

    /**
     * 排场操作类
     */
    @Autowired
    ChipFormationService chipFormationService;

    /**
     * 获得该用户订单
     * @param userId 用户编号
     * @return
     */
    @RequestMapping("/order")
    public String  getOrder(@RequestParam("userId")Integer userId){
        List<MovieTicket> ticketList = movieTicketService.getMovieTickets(userId);

        for (MovieTicket movieTicket:ticketList){
            movieTicketMap.put(movieTicket.getOrder(),movieTicket);
        }

        return JSON.toJSONString(movieTicketMap);
    }

    /**
     * 生成订单
     * @return
     * ?cId=1
     * &cfId=1
     * &chId=1
     * &mId=1
     * &layout="2-2-2-2-2-2-2-1-1-1-1-1-0-2-2-1-1-1-1-1-1-1-1-1-1-0-1-1-1-1-1-1-1-1-1-1-1-2-2-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-1-1-1-1-1-1-1-1-1-2-1"
     * &column=13
     * &ticketPrice=43
     * &yzm=123456
     * &phoneNumbers=
     * &seatName="{1:'1排1座'}"
     */
    @RequestMapping("/gender")
    @ResponseBody
    public String generateOrder( @RequestParam("cId")Integer cId,//1
                                 @RequestParam("cfId")Integer cfId,//1
                                 @RequestParam("chId")Integer chId,//1
                                 @RequestParam("mId")Integer mId,//1
                                 @RequestParam("layout") String  layout,//2-2-2-2-2-2-2-1-1-1-1-1-0-2-2-1-1-1-1-1-1-1-1-1-1-0-1-1-1-1-1-1-1-1-1-1-1-2-2-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-1-1-1-1-1-1-1-1-1-2-1
                                 @RequestParam("column")Integer column,//13
                                 @RequestParam("ticketPrice")double ticketPrice,//43
                                 @RequestParam("yzm")String yzm,//123456
                                 @RequestParam("phoneNumbers")String phoneNumbers,//18529515196
                                 @RequestParam("seatName") String seatName//{1:'1排1座'}
    ) {
        //防止数据错误，进入该方法清空
        list.clear();
        if (!redisUtil.hasKey("cId"+cId)){
            redisUtil.set("cId"+cId,JSON.toJSONString(cinemaService.getCinema(cId)));
        }
        if (!redisUtil.hasKey("cfId"+cfId)){
            redisUtil.set("cfId"+cfId,JSON.toJSONString(chipFormationService.getChipFormation(cfId)));
        }
        if (!redisUtil.hasKey("mId"+mId)){
            redisUtil.set("mId"+mId,JSON.toJSONString(movieService.getMovie(mId)));
        }
        System.out.println(cinemaHallService.getCinemaHall(chId).getLayout());
        if (!redisUtil.hasKey("chId"+chId)){
            redisUtil.set("chId"+chId,JSON.toJSONString(cinemaHallService.getCinemaHall(chId)));
        }

        if (yzm.equals(redisUtil.get(phoneNumbers))){
            try {
                movieTicket = new MovieTicket();

                User user = userService.getUser(phoneNumbers);

                //返回为空证明未注册
                if (null == user){
                    return "false";
                }

                //从redis中读取数据

                movieTicket.setUId(user.getId());
                movieTicket.setCinemaId(cId);
                movieTicket.setChipFormationId(cfId);
                movieTicket.setMovieHallId(chId);
                movieTicket.setMovieId(mId);
                movieTicket.setTicketPrice(ticketPrice);
                chipFormation.setCinemaHall(new CinemaHall(layout,column));
                movieTicket.setChipFormation(chipFormation);

                Map map = (Map)JSON.parse(seatName);

                Calendar calendar = Calendar.getInstance();

                String seat = "";

                for (Object o : map.values()) {
                    seat = calendar.get(calendar.YEAR)+""
                            +(calendar.get(Calendar.MONTH)+1)
                            +calendar.get(Calendar.DATE)
                            +movieTicket.getCinemaId().toString()
                            +movieTicket.getMovieHallId().toString()
                            +movieTicket.getChipFormationId()
                            +movieTicket.getMovieId().toString()
                            + OrderUtil.extract(o.toString());
                    if (redisUtil.hasKey(seat)){
                        System.out.println("键已存在！处理退出！");
                        return "false";
                    }
                    list.add(new OrderSeat(null,null,null,new Seat(o.toString())));
                }

                //按照订单生成规则生成订单号
                String order = "or"+movieTicket.getUId().toString()+""
                        +calendar.get(calendar.YEAR)
                        +(calendar.get(Calendar.MONTH)+1)
                        +calendar.get(Calendar.DATE)
                        +calendar.get(Calendar.HOUR_OF_DAY)
                        +calendar.get(Calendar.MINUTE)
                        +movieTicket.getCinemaId()
                        +movieTicket.getMovieHallId()
                        +movieTicket.getChipFormationId()
                        +movieTicket.getMovieId();

                System.out.println("订单号=============>"+order);

                //设置订单号
                movieTicket.setOrder(order);

                //添加订单座位集合
                movieTicket.setOrderSeatList(list);

                //插入队列
                msgProducer.sendMsgA(JSON.toJSONString(movieTicket));

                //删除该键
                redisUtil.del(phoneNumbers);

                //验证通过后，重新存入，将用户集合保存在redis中
                redisUtil.set(phoneNumbers,JSON.toJSONString(user));

                return movieTicket.getOrder();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "false";
            }
        }else{
            return "false";
        }
    }

    @RequestMapping("/yz")
    @ResponseBody
    public String yz(){

        return "";
    }

    /**
     * 结算 220191112111
     * @param order 订单号
     * @return
     */
    @RequestMapping("/settlement")
    @ResponseBody
    public String Settlement(@RequestParam("order")String order){
        //判断订单是否存在
        if (redisUtil.hasKey(order)){
            msgProducer.sendMsgB(redisUtil.get(order));
            return "true";
        }
        //没有
        return "false";
    }

    /**
     *
     * @param layoutNum 影厅布局号
     * @return
     */
    @RequestMapping("/getLayout")
    @ResponseBody
    public String getLayout(@RequestParam("layoutNum")String layoutNum){
        return redisUtil.get(layoutNum);
    }

    @RequestMapping("/deleteKey")
    @ResponseBody
    public String  deleteKey(String key){
        return redisUtil.del(key)+"";
    }

    @RequestMapping("/phoneNumbers")
    @ResponseBody
    public String  phKey(@RequestParam("phoneNumbers")String phoneNumbers){
        return redisUtil.get(phoneNumbers)+"";
    }

    /**
     * 获取影票
     * @param order
     * @return
     */
    @RequestMapping("/getmovietticket")
    @ResponseBody
    public String  getmovietticket(@RequestParam("order")String order){
        //通过订单号返回数据
        JSONObject jsonObject  = JSONObject.parseObject(redisUtil.get(order));
        MovieTicket movieTicket = JSONObject.toJavaObject(jsonObject, MovieTicket.class);//得到订单对象

        jsonObject  = JSONObject.parseObject(redisUtil.get("mId"+movieTicket.getMovieId()));
        Movie movie = JSONObject.toJavaObject(jsonObject, Movie.class);//得到电影对象

        jsonObject  = JSONObject.parseObject(redisUtil.get("chId"+movieTicket.getMovieHallId()));
        CinemaHall cinemaHall = JSONObject.toJavaObject(jsonObject, CinemaHall.class);//得到影厅对象

        jsonObject  = JSONObject.parseObject(redisUtil.get("cId"+movieTicket.getCinemaId()));
        Cinema cinema = JSONObject.toJavaObject(jsonObject, Cinema.class);//得到影院对象

        jsonObject  = JSONObject.parseObject(redisUtil.get("cfId"+movieTicket.getChipFormationId()));
        ChipFormation chipFormation = JSONObject.toJavaObject(jsonObject, ChipFormation.class);//得到排片对象

        chipFormation.setCinemaHall(cinemaHall);
        chipFormation.setMovie(movie);

        movieTicket.setCinema(cinema);
        movieTicket.setChipFormation(chipFormation);

        return JSON.toJSONString(movieTicket);
    }

    @RequestMapping("/getch")
    @ResponseBody
    public void getph(Integer chId){
        CinemaHall cinemaHall = cinemaHallService.getCinemaHall(chId);
        cinemaHall.setLayout(OrderUtil.join(cinemaHall.getLayout()));
        redisUtil.set("lo"+cinemaHall.getCinemaId()+""+cinemaHall.getId(),JSON.toJSONString(cinemaHall));
    }

    @RequestMapping("/keys")
    @ResponseBody
    public String  keys(String key){
        return JSON.toJSONString(redisUtil.keys(key));
    }

    @Autowired
    AlipayService alipayService;

    @RequestMapping("/pay")
    @ResponseBody
    public String payMent(HttpServletResponse response, HttpServletRequest request,String orderNo){

        JSONObject jsonObject  = JSONObject.parseObject(redisUtil.get(orderNo));
        MovieTicket movieTicket = JSONObject.toJavaObject(jsonObject, MovieTicket.class);//得到订单对象

        alipayService.aliPay(response,request,movieTicket);

        return movieTicket.getQrcodeRoute();
    }
}
