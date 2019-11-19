package com.hawkeye.mapper;

import org.apache.ibatis.annotations.Param;

public interface OrderSeatMapper {

    /**
     * #{ticketOrder},#{seatId}
     * @return
     */
    Integer addOrderSeat(@Param("ticketOrder")Integer ticketOrder,
                         @Param("seatId")Integer seatId);

    Integer getMaxOsid();
}
