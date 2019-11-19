package com.hawkeye.service.impl;

import com.hawkeye.mapper.ChipFormationMapper;
import com.hawkeye.mapper.CinemaMapper;
import com.hawkeye.mapper.CinemaMovieMapper;
import com.hawkeye.pojo.Cinema;
import com.hawkeye.service.CinemaService;
import com.hawkeye.vo.ChipFormationVo;
import com.hawkeye.vo.CinemaMovieVo;
import com.hawkeye.vo.CinemaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaMapper cinemaMapper;
    @Autowired
    private CinemaMovieMapper cinemaMovieMapper;
    @Autowired
    private ChipFormationMapper chipFormationMapper;

    @Override
    public Cinema getCinema(Integer id) {
        return cinemaMapper.getCinema(id);
    }
	

    @Override
    public List<Cinema> getCinemas(Map<String, Integer> obj) {
        return cinemaMapper.getCinemas(obj);
    }

    @Override
    public CinemaVo getCinemaVo(Integer cinemaId) {
        CinemaVo cv = cinemaMapper.getCinemaVo(cinemaId);
        System.out.println(cv.getImgRoute());
        List<CinemaMovieVo> cmv = cinemaMovieMapper.getCinemaMoviesByCinemaId(cinemaId);
        System.out.println(cmv.size());
        System.out.println(cmv.get(0).getMovieMainTitle());
        List<ChipFormationVo> cfv = chipFormationMapper.getChipFormationsByCinemaId(cinemaId);
//        for (CinemaMovieVo item : cmv
//        ) {
//            item.setChipFormationVos(new ArrayList<ChipFormationVo>());
//            for (ChipFormationVo items : cfv
//            ) {
//                if (item.getMovieId() == items.getMovieId()) {
//                    item.getChipFormationVos().add(items);
//                }
//            }
//        }
        cmv.forEach(item -> {
            item.setChipFormationVos(new ArrayList<ChipFormationVo>());
            cfv.forEach(items -> {
                if (item.getMovieId() == items.getMovieId()) {
                    item.getChipFormationVos().add(items);
                }
            });
        });
        cv.setCinemaMovieVos(cmv);
        return cv;
    }
}
