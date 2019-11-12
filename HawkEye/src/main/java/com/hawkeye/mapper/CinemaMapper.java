package com.hawkeye.mapper;

import com.hawkeye.pojo.Cinema;

import java.util.List;
import java.util.Map;

public interface CinemaMapper {
    /**
     * 查询所有影院信息可以使用条件筛选
     * @param obj 筛选条件
     * @return
     */
    List<Cinema> getCinemas(Map<String, Integer> obj);
}
