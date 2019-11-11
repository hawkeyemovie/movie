package com.hawkeye.controller;

import com.alibaba.fastjson.JSON;
import com.hawkeye.service.MovieTicketService;
import com.hawkeye.service.OrderSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购票流程控制器
 */
@RestController
@RequestMapping("/shop")
public class ShoppingController {

    /**
     * 订单（电影票）
     */
    @Autowired
    MovieTicketService movieTicketService;

    /**
     * 座位编号
     */
    @Autowired
    OrderSeatService orderSeatService;

    /**
     * 获得订单编号
     * @param userId 用户编号
     * @return
     */
    @RequestMapping("/movieTicket")
    public String  getmovieTicket(@RequestParam("userId")Integer userId){
        return JSON.toJSONString(movieTicketService.getMovieTickets(userId));
    }

    /**
     * 获得座位编号
     * @param order 订单号
     * @return
     */
    @RequestMapping("/OrderSeat")
    public String getOrderSeat(@RequestParam("order")String order){
        return JSON.toJSONString(orderSeatService.getOrderSeats(order));
    }




}
