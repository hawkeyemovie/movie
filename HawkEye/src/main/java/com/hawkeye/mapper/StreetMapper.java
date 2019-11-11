package com.hawkeye.mapper;

import com.hawkeye.pojo.Street;

import java.util.List;

public interface StreetMapper {
    /**
     * 查询区(县)下的所有街道
     * @param id 区(县)编号
     * @return
     */
    List<Street> getStreetByAreaId(Integer id);
}
