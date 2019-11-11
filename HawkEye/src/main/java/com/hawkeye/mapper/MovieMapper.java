package com.hawkeye.mapper;

import com.hawkeye.pojo.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MovieMapper {
    /**
     *
     * @param typeId 类型ID
     * @param regionId 区域ID
     * @param release 年代
     * @return
     */
    List<Movie> getMovies(@Param("typeId")Integer typeId,@Param("regionId")Integer regionId,@Param("release")String release);
}
