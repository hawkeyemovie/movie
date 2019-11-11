package com.hawkeye.service;

import com.hawkeye.pojo.Movie;

import java.util.List;

public interface MovieService {
    /**
     *
     * @param typrId 类型ID
     * @param regionId 区域ID
     * @param release 年代
     * @return
     */
    List<Movie> getMovies(Integer typrId,Integer regionId,String release);
}
