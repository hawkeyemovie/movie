package com.hawkeye.service;

public interface SeatService {
    Integer addSeat(Integer cinemaHallId,
                    String seatName,
                    Integer row,
                    Integer column);

    Integer getMaxSid();
}
