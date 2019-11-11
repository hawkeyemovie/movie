package com.hawkeye.mapper.remapper;


import com.hawkeye.repojo.Popular;

import java.util.List;

/**
 * 电影中的正在热映电影
 */
public interface PopularMapper {
    /**
     * 获取所有热门数据
     * @return
     */
    List<Popular> getPopular();
}
