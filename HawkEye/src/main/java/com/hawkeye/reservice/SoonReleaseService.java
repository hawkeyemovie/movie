package com.hawkeye.reservice;


import com.hawkeye.repojo.SoonRelease;

import java.util.List;

public interface SoonReleaseService {
    /**
     * 获取即将上映的电音
     * @return
     */
    List<SoonRelease> getSoonRelease();
}
