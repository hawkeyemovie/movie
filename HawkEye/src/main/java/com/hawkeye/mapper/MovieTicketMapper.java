package com.hawkeye.mapper;

import com.hawkeye.pojo.MovieTicket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MovieTicketMapper {
    List<MovieTicket> getMovieTickets(@Param("userId")Integer userId);
}
