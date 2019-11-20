package com.hawkeye.service;

import com.hawkeye.pojo.Street;

import java.util.List;

public interface StreetService {
    List<Street> getStreetByAreaId(Integer id);
}
