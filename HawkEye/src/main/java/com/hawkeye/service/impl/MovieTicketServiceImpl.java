package com.hawkeye.service.impl;

import com.hawkeye.mapper.MovieTicketMapper;
import com.hawkeye.pojo.MovieTicket;
import com.hawkeye.service.MovieTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovieTicketServiceImpl implements MovieTicketService {

    @Autowired
    MovieTicketMapper movieTicketMapper;

    @Override
    public List<MovieTicket> getMovieTickets(Integer userId) {
        return movieTicketMapper.getMovieTickets(userId);
    }

    @Override
    public Integer addTicket(String order, String ticketCode, String qrcodeRoute, Integer movieId, Integer cinemaId, Integer movieHallId, Integer chipFormationId, Double ticketPrice, Integer payState, Date foundTime, Integer userId) {
        return movieTicketMapper.addTicket(order,ticketCode,qrcodeRoute,movieId,cinemaId,movieHallId,chipFormationId,ticketPrice,payState,foundTime,userId);
    }

    @Override
    public Integer getMaxId() {
        return movieTicketMapper.getMaxId();
    }
}
