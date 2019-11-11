package com.hawkeye.reservice;


import com.hawkeye.repojo.Popular;

import java.util.List;

public interface ClassicService {
    /**
     * 获取所有经典电影
     * @return
     */
    List<Popular> HomeGetClassic();

    /**
     * 导航栏电影，中经典电影的请求方法
     * @param typeId
     * @param regionId
     * @param year
     * @return
     */
    List<Popular> getClassic(String typeId, String regionId, String year);
}
