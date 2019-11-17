package com.hawkeye.mapper;

import com.hawkeye.vo.ChipFormationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChipFormationMapper {
    /**
     * 获取影院的电影所有场次信息
     * @param cinemaId 影院编号
     * @return
     */
    List<ChipFormationVo> getChipFormationsByCinemaId(@Param("cinemaId") Integer cinemaId);
}
