package com.hawkeye.mapper;

import com.hawkeye.pojo.MovieTicket;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Date;
import java.util.List;

public interface MovieTicketMapper {
    List<MovieTicket> getMovieTickets(@Param("userId")Integer userId);

    //#{order}, #{ticketCode}, #{qrcodeRoute}, #{movieId}, #{cinemaId},#{movieHallId, #{chipFormationId}, #{ticketPrice},#{payState},#{foundTime},#{userId}

    Integer addTicket(@Param("order")String order,
                      @Param("ticketCode")String ticketCode,
                      @Param("qrcodeRoute")String qrcodeRoute,
                      @Param("movieId")Integer movieId,
                      @Param("cinemaId")Integer cinemaId,
                      @Param("movieHallId")Integer movieHallId,
                      @Param("chipFormationId")Integer chipFormationId,
                      @Param("ticketPrice")Double ticketPrice,
                      @Param("payState")Integer payState,
                      @Param("foundTime")Date foundTime,
                      @Param("userId")Integer userId);

    Integer getMaxId();
}
