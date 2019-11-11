package com.hawkeye.service;

import com.hawkeye.pojo.OrderSeat;

import java.util.List;

public interface OrderSeatService {
    List<OrderSeat> getOrderSeats(String order);
}
