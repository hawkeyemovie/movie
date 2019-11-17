package com.hawkeye.mapper;

import com.hawkeye.vo.CinemaMovieVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CinemaMovieMapper {
    /**
     * 获取影院所有电源
     * @param cinemaId 影院编号
     * @return
     */
    List<CinemaMovieVo> getCinemaMoviesByCinemaId(@Param("cinemaId") Integer cinemaId);
}
