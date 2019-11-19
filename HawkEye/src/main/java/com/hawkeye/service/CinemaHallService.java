package com.hawkeye.service;

import com.hawkeye.pojo.CinemaHall;

public interface CinemaHallService {
    Integer upCinemaHall(String layout,Integer chId);

    CinemaHall getCinemaHall(Integer id);
}
