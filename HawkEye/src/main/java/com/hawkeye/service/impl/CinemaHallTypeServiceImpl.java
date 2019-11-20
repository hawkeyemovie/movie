package com.hawkeye.service.impl;

import com.hawkeye.mapper.CinemaHallTypeMapper;
import com.hawkeye.pojo.CinemaHallType;
import com.hawkeye.service.CinemaHallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CinemaHallTypeServiceImpl implements CinemaHallTypeService {
    @Autowired
    private CinemaHallTypeMapper cinemaHallTypeMapper;
    @Override
    public List<CinemaHallType> getSpecialHalls() {
        return cinemaHallTypeMapper.getSpecialHalls();
    }
}
