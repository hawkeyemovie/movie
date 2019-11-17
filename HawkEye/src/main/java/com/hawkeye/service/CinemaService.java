package com.hawkeye.service;

import com.hawkeye.pojo.Cinema;
import com.hawkeye.vo.CinemaVo;

import java.util.List;
import java.util.Map;

public interface CinemaService {
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
    CinemaVo getCinema(Integer cinemaId);
}
