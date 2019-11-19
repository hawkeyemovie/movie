package com.hawkeye.service;


public interface OrderSeatService {

    Integer addOrderSeat(Integer ticketOrder, Integer seatId);

    Integer getMaxOsid();
}
