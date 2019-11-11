package com.hawkeye.controller;

import com.alibaba.fastjson.JSON;
import com.hawkeye.elservice.ElCinemaService;
import com.hawkeye.elservice.ElFilmmakerService;
import com.hawkeye.elservice.ElMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/el")
public class ElController {
    /**
     * 影院
     */
    @Autowired
    ElCinemaService elCinemaService;

    /**
     * 影视剧
     */
    @Autowired
    ElMoviesService elMoviesService;

    /**
     * 影人
     */
    @Autowired
    ElFilmmakerService elFilmmakerService;

    /**
     *使拦截器实现跨域请求
     * @param search 搜索条件
    * @return
     */
    @RequestMapping("/elCinema")
    public String  test1(String search){
        return JSON.toJSONString(elCinemaService.getElCinema(search));
    }

    @RequestMapping("/elMovies")
    public String test2(String search){
        return JSON.toJSONString(elMoviesService.getMovie(search));
    }

    @RequestMapping("/elFilmmaker")
    public String test3(String search){
        return JSON.toJSONString(elFilmmakerService.getFilmmakers(search));
    }

}
