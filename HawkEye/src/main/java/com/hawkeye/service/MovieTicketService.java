package com.hawkeye.service;

import com.hawkeye.pojo.MovieTicket;

import java.util.List;

public interface MovieTicketService {
    List<MovieTicket> getMovieTickets(Integer userId);
}
