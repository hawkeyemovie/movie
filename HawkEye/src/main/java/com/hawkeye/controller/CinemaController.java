package com.hawkeye.controller;

import com.alibaba.fastjson.JSON;
import com.hawkeye.mapper.CinemaHallTypeMapper;
import com.hawkeye.service.AreaService;
import com.hawkeye.service.CinemaBrandService;
import com.hawkeye.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kawa on 2019/11/19.
 */
@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private CinemaBrandService cinemaBrandService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private CinemaHallTypeMapper cinemaHallTypeMapper;

    @RequestMapping("/detailed/{cinemaId}")
    public String detailed(@PathVariable("cinemaId") Integer cinemaId) {
        return JSON.toJSONString(cinemaService.getCinemaVo(cinemaId));
    }

    @RequestMapping("/list")
    public String list(){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("1",cinemaBrandService.getCinemaBrands());
        obj.put("2",areaService.getAreaByCityId(1));
        obj.put("3",cinemaHallTypeMapper.getSpecialHalls());
        obj.put("4",cinemaService.getCinemas(null));
        return JSON.toJSONString(obj);
    }
}
