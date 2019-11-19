package com.hawkeye.service.impl;

import com.hawkeye.mapper.OrderSeatMapper;
import com.hawkeye.service.OrderSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSeatServiceImpl implements OrderSeatService {

    @Autowired
    OrderSeatMapper orderSeatMapper;

    @Override
    public Integer addOrderSeat(Integer ticketOrder, Integer seatId) {
        return orderSeatMapper.addOrderSeat(ticketOrder,seatId);
    }

    @Override
    public Integer getMaxOsid() {
        return orderSeatMapper.getMaxOsid();
    }
}
