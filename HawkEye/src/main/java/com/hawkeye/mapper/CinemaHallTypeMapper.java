package com.hawkeye.mapper;

import com.hawkeye.pojo.CinemaHallType;

import java.util.List;

public interface CinemaHallTypeMapper {
    /**
     * 查询所有特殊厅
     * @return 特殊厅集合
     */
    List<CinemaHallType> getSpecialHalls();
}
