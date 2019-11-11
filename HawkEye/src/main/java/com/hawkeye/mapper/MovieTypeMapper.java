package com.hawkeye.mapper;

import com.hawkeye.pojo.MovieType;

import java.util.List;

public interface MovieTypeMapper {
    /**
     * 获取所有电影类型
     * @return
     */
   List<MovieType> getMovieTypes();
}
