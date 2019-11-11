package com.hawkeye.mapper;

import com.hawkeye.pojo.Area;

import java.util.List;

public interface AreaMapper {
    /**
     * 查询城市下的所有区(县)
     * @param id 城市编号
     * @return
     */
    List<Area> getAreaByCityId(Integer id);
}
