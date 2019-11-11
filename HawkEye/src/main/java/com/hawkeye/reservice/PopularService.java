package com.hawkeye.reservice;


import com.hawkeye.repojo.Popular;

import java.util.List;

public interface PopularService {
    /**
     * 获取所有热门数据
     * @return
     */
    List<Popular> getPopular();
}
