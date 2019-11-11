package com.hawkeye.elservice;

import com.hawkeye.elpojo.ElFilmmaker;

import java.util.List;

public interface ElFilmmakerService {
    List<ElFilmmaker> getFilmmakers(String search);
}
