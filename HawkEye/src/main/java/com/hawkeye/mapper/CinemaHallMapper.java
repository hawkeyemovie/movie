package com.hawkeye.mapper;

import com.hawkeye.pojo.CinemaHall;
import org.apache.ibatis.annotations.Param;

public interface CinemaHallMapper {

    Integer upCinemaHall(@Param("layout")String layout,
                         @Param("chId")Integer chId);

    CinemaHall getCinemaHall(@Param("id")Integer id);
}
