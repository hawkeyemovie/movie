package com.hawkeye.service.impl;

import com.hawkeye.mapper.SeatMapper;
import com.hawkeye.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    SeatMapper seatMapper;

    @Override
    public Integer addSeat(Integer cinemaHallId, String seatName, Integer row, Integer column) {
        return seatMapper.addSeat(cinemaHallId,seatName,row,column);
    }

    @Override
    public Integer getMaxSid() {
        return seatMapper.getMaxSid();
    }
}
