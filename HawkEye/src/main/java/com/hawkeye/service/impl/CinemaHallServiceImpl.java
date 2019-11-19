package com.hawkeye.service.impl;

import com.hawkeye.mapper.CinemaHallMapper;
import com.hawkeye.pojo.CinemaHall;
import com.hawkeye.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {

    @Autowired
    CinemaHallMapper cinemaHallMapper;

    @Override
    public Integer upCinemaHall(String layout, Integer chId) {
        return cinemaHallMapper.upCinemaHall(layout,chId);
    }

    @Override
    public CinemaHall getCinemaHall(Integer id) {
        return cinemaHallMapper.getCinemaHall(id);
    }
}
