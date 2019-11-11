package com.hawkeye.service.impl;

import com.hawkeye.mapper.MovieRegionMapper;
import com.hawkeye.pojo.MovieRegion;
import com.hawkeye.service.MovieRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieRegionServiceImpl implements MovieRegionService {

    @Autowired
    MovieRegionMapper movieRegionMapper;

    @Override
    public List<MovieRegion> getMovieRegions() {

        return movieRegionMapper.getMovieRegions();

    }
}
