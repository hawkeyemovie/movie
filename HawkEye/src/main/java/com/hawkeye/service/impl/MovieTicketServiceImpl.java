package com.hawkeye.service.impl;

import com.hawkeye.mapper.MovieTicketMapper;
import com.hawkeye.pojo.MovieTicket;
import com.hawkeye.service.MovieTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieTicketServiceImpl implements MovieTicketService {

    @Autowired
    MovieTicketMapper movieTicketMapper;

    @Override
    public List<MovieTicket> getMovieTickets(Integer userId) {
        return movieTicketMapper.getMovieTickets(userId);
    }
}
