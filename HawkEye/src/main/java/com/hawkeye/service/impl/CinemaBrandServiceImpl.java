package com.hawkeye.service.impl;

import com.hawkeye.mapper.CinemaBrandMapper;
import com.hawkeye.pojo.CinemaBrand;
import com.hawkeye.service.CinemaBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CinemaBrandServiceImpl implements CinemaBrandService {
    @Autowired
    private CinemaBrandMapper cinemaBrandMapper;
    @Override
    public List<CinemaBrand> getCinemaBrands() {
        return cinemaBrandMapper.getCinemaBrands();
    }
}
