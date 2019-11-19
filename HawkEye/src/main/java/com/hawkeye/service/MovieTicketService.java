package com.hawkeye.service;

import com.hawkeye.pojo.MovieTicket;

import java.util.Date;
import java.util.List;

public interface MovieTicketService {
    List<MovieTicket> getMovieTickets(Integer userId);

    Integer addTicket(String order,
                      String ticketCode,
                      String qrcodeRoute,
                      Integer movieId,
                      Integer cinemaId,
                      Integer movieHallId,
                      Integer chipFormationId,
                      Double ticketPrice,
                      Integer payState,
                      Date foundTime,
                      Integer userId);

    Integer getMaxId();
}
