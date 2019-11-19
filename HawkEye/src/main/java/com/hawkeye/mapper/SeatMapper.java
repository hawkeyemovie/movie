package com.hawkeye.mapper;

import org.apache.ibatis.annotations.Param;

public interface SeatMapper {

    /**
     * 添加座位
     * #{cinemaHallId},#{seatName},#{row},#{column}
     * @return
     */
    Integer addSeat(@Param("cinemaHallId")Integer cinemaHallId,
                    @Param("seatName")String seatName,
                    @Param("row")Integer row,
                    @Param("column")Integer column);

    /**
     * 座位最大id
     * @return
     */
    Integer getMaxSid();
}
