package com.hawkeye.vo;

import java.util.List;

public class ChipFormationVo {
    private Integer movieId;
    private String filmingTime;
    private List<ChipFormationListVo> chipFormationListVos;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getFilmingTime() {
        return filmingTime;
    }

    public void setFilmingTime(String filmingTime) {
        this.filmingTime = filmingTime;
    }

    public List<ChipFormationListVo> getChipFormationListVos() {
        return chipFormationListVos;
    }

    public void setChipFormationListVos(List<ChipFormationListVo> chipFormationListVos) {
        this.chipFormationListVos = chipFormationListVos;
    }
}
