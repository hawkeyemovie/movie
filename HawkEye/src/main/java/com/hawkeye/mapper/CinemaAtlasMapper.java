package com.hawkeye.mapper;

import com.hawkeye.pojo.CinemaAtlas;
import org.apache.ibatis.annotations.Param;

public interface CinemaAtlasMapper {
    /**
     * 查询电影院图集通过电影院编号
     * @param cinemaId 电影院编号
     * @return
     */
    CinemaAtlas getCinemaAtlasByCinemaId(@Param("cinemaId") Integer cinemaId);
}
