package com.hawkeye.reservice.impl;

import com.hawkeye.mapper.remapper.PopularMapper;
import com.hawkeye.repojo.Popular;
import com.hawkeye.reservice.PopularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopularServiceImpl implements PopularService {
    @Autowired
    PopularMapper popularMapper;

    /**
     * 获取所有热门数据
     * @return
     */
    @Override
    public List<Popular> getPopular() {
        return popularMapper.getPopular();
    }
}
