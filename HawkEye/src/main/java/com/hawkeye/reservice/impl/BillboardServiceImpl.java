package com.hawkeye.reservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hawkeye.pojo.Movie;
import com.hawkeye.mapper.remapper.BillboardMapper;
import com.hawkeye.reservice.BillboardService;
import com.hawkeye.util.RedisKeys;
import com.hawkeye.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillboardServiceImpl implements BillboardService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    BillboardMapper billboardMapper;

    /**
     * 获取最受期待数据
     * @return
     */
    @Override
    public List<Movie> getMostAnticipated() {

        String str = redisUtil.get(RedisKeys.MOSTANTICIPATED);
        List<Movie> strMostAnticipated = null;
        if(str == null ){
            str = JSON.toJSONString(billboardMapper.getMostAnticipated());
            redisUtil.set(RedisKeys.MOSTANTICIPATED,str);
        }
        strMostAnticipated = JSONArray.parseArray(str,Movie.class);
        return strMostAnticipated;
    }

    /**
     * 获取TOP榜数据
     * @param displayNum
     * @return
     */
    @Override
    public List<Movie> getTOPBill(Integer displayNum) {
        String str = redisUtil.get(RedisKeys.TOP);
        List<Movie> strTOP = null;
        if(str == null){
            str = JSON.toJSONString(billboardMapper.getTOPBill());
            redisUtil.set(RedisKeys.TOP,str);
        }
        strTOP = JSONArray.parseArray(str,Movie.class);
        if(displayNum == 0){
            return strTOP.subList(0,1);
        }
        return strTOP;
    }
}
