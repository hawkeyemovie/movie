package com.hawkeye.reservice.impl;

import com.hawkeye.mapper.remapper.SoonReleaseMapper;
import com.hawkeye.repojo.SoonRelease;
import com.hawkeye.reservice.SoonReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoonReleaseServiceImpl implements SoonReleaseService {

    @Autowired
    SoonReleaseMapper soonReleaseMapper;
    @Override
    public List<SoonRelease> getSoonRelease() {
        return soonReleaseMapper.getSoonRelease();
    }
}
