package com.hawkeye.service.impl;

import com.hawkeye.mapper.MovieMapper;
import com.hawkeye.pojo.Movie;
import com.hawkeye.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieMapper movieMapper;

//    @Cacheable(value = "Movies")//redis缓存注解
    @Override
    public List<Movie> getMovies(Integer typrId, Integer regionId, String release) {
        return movieMapper.getMovies(typrId,regionId,release);
    }
}
