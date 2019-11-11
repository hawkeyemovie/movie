package com.hawkeye.service.impl;

import com.hawkeye.mapper.OrderSeatMapper;
import com.hawkeye.pojo.OrderSeat;
import com.hawkeye.service.OrderSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSeatServiceImpl implements OrderSeatService {

    @Autowired
    OrderSeatMapper orderSeatMapper;

    @Override
    public List<OrderSeat> getOrderSeats(String order) {
        return orderSeatMapper.getOrderSeats(order);
    }
}
