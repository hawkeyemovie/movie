package com.hawkeye.mapper;

import com.hawkeye.pojo.OrderSeat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderSeatMapper {
    List<OrderSeat> getOrderSeats(@Param("order")String order);
}
