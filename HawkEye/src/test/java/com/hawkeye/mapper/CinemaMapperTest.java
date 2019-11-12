package com.hawkeye.mapper;

import com.hawkeye.HawkeyeApplicationTests;
import com.hawkeye.pojo.Cinema;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CinemaMapperTest extends HawkeyeApplicationTests {
    @Autowired
    private CinemaMapper cinemaMapper;

    @Test
    void getCinemas() {
        Map<String, Integer> obj = new HashMap<>();
        obj.put("cinemaBrandId", 13);
        List<Cinema> list = cinemaMapper.getCinemas(obj);
        System.out.println(list.size());
    }
}