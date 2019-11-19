package com.hawkeye.service.impl;

import com.hawkeye.mapper.ChipFormationMapper;
import com.hawkeye.pojo.ChipFormation;
import com.hawkeye.service.ChipFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChipFormationServiceImpl implements ChipFormationService {

    @Autowired
    ChipFormationMapper chipFormationMapper;

    @Override
    public ChipFormation getChipFormation(Integer cfId) {
        return chipFormationMapper.getChipFormation(cfId);
    }
}
