package com.hawkeye.elservice;

import com.hawkeye.elpojo.ElCinema;

import java.util.List;


public interface ElCinemaService {
    List<ElCinema> getElCinema(String search);
}
