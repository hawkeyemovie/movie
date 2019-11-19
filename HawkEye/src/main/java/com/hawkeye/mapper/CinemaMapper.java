package com.hawkeye.mapper;

import com.hawkeye.pojo.Cinema;
import com.hawkeye.vo.CinemaVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CinemaMapper {

    Cinema getCinema(@Param("id")Integer id);
	/**
     * 查询所有影院信息可以使用条件筛选
     * @param obj 筛选条件
     * @return
     */
    List<Cinema> getCinemas(Map<String, Integer> obj);

    /**
     * 查询影院信息
     * @param cinemaId 影院编号
     * @return
     */
    CinemaVo getCinemaVo(@Param("cinemaId") Integer cinemaId);
}
