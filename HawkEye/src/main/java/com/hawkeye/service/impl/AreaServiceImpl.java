package com.hawkeye.service.impl;

import com.hawkeye.mapper.AreaMapper;
import com.hawkeye.pojo.Area;
import com.hawkeye.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;
    @Override
    public List<Area> getAreaByCityId(Integer id) {
        return areaMapper.getAreaByCityId(id);
    }
}
