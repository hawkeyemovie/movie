package com.hawkeye.service.impl;

import com.hawkeye.mapper.MovieTypeMapper;
import com.hawkeye.pojo.MovieType;
import com.hawkeye.service.MovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieTypeServiceImpl implements MovieTypeService {

    @Autowired
    MovieTypeMapper movieTypeMapper;

    @Override
    public List<MovieType> getMovieTypes() {
        return movieTypeMapper.getMovieTypes();
    }
}
