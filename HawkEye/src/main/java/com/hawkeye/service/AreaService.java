package com.hawkeye.service;

import com.hawkeye.pojo.Area;

import java.util.List;

public interface AreaService {
    /**
     * 查询城市下的所有区(县)
     * @param id 城市编号
     * @return
     */
    List<Area> getAreaByCityId(Integer id);
}
