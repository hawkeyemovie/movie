package com.hawkeye.elservice;

import com.hawkeye.elpojo.ElMovies;

import java.util.List;

public interface ElMoviesService {
    List<ElMovies> getMovie(String search);
}
