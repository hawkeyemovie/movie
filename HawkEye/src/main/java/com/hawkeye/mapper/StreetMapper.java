package com.hawkeye.mapper;

import com.hawkeye.pojo.Street;

import java.util.List;

public interface StreetMapper {
    List<Street> getStreetByAreaId(Integer id);
}
