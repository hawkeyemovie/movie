package com.hawkeye.service.impl;

import com.hawkeye.mapper.StreetMapper;
import com.hawkeye.pojo.Street;
import com.hawkeye.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public List<Street> getStreetByAreaId(Integer id) {
        return streetMapper.getStreetByAreaId(id);
    }
}
