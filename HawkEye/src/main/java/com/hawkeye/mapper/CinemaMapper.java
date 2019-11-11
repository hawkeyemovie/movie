package com.hawkeye.mapper;

import com.hawkeye.pojo.Cinema;

import java.util.List;

public interface CinemaMapper {
    /**
     * 查询所有影院信息
     * @return
     */
    List<Cinema> getCinemas();
}
