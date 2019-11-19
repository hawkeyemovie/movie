package com.hawkeye.controller;

import com.alibaba.fastjson.JSON;
import com.hawkeye.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kawa on 2019/11/19.
 */
@RestController
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @RequestMapping("/detailed/{cinemaId}")
    public String detailed(@PathVariable("cinemaId") Integer cinemaId) {
        return JSON.toJSONString(cinemaService.getCinemaVo(cinemaId));
    }
}
