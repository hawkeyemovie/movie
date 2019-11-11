package com.hawkeye.mapper;

import com.hawkeye.pojo.City;

import java.util.List;

public interface CityMapper {
    /**
     * 查询所有城市
     * @return
     */
    List<City> getCitys();
}
