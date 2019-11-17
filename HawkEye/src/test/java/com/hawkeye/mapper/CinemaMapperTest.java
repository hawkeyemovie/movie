package com.hawkeye.mapper;

import com.alibaba.fastjson.JSON;
import com.hawkeye.HawkeyeApplicationTests;
import com.hawkeye.vo.ChipFormationVo;
import com.hawkeye.vo.CinemaMovieVo;
import com.hawkeye.vo.CinemaVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CinemaMapperTest extends HawkeyeApplicationTests {
    @Autowired
    private CinemaMapper cinemaMapper;
    @Autowired
    private CinemaMovieMapper cinemaMovieMapper;
    @Autowired
    private ChipFormationMapper chipFormationMapper;

    @Test
    void Tests() {
        CinemaVo cv = cinemaMapper.getCinema(1);
        List<CinemaMovieVo> cmv = cinemaMovieMapper.getCinemaMoviesByCinemaId(1);
        List<ChipFormationVo> cfv = chipFormationMapper.getChipFormationsByCinemaId(1);
        for (CinemaMovieVo item : cmv
        ) {
            item.setChipFormationVos(new ArrayList<ChipFormationVo>());
            for (ChipFormationVo items : cfv
            ) {
                if (item.getMovieId() == items.getMovieId()) {
                    item.getChipFormationVos().add(items);
                }
            }
        }
//        cmv.forEach(i -> {
//            i.setChipFormationVos(new ArrayList<ChipFormationVo>());
//            cfv.forEach(k -> {
//                if (i.getMovieId() == k.getMovieId()) {
//                    i.getChipFormationVos().add(k);
//                }
//            });
//        });
        cv.setCinemaMovieVos(cmv);
        String json = JSON.toJSONString(cv);
        System.out.println(json);
    }
}