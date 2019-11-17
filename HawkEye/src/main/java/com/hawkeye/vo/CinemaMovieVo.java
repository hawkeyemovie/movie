package com.hawkeye.vo;

import java.util.List;

public class CinemaMovieVo {
    private Integer movieId;
    private String movieMainTitle;
    private double score;
    private Integer movieDuration;
    private String typeName;
    private String toStar;
    private List<ChipFormationVo> chipFormationVos;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieMainTitle() {
        return movieMainTitle;
    }

    public void setMovieMainTitle(String movieMainTitle) {
        this.movieMainTitle = movieMainTitle;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Integer getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(Integer movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getToStar() {
        return toStar;
    }

    public void setToStar(String toStar) {
        this.toStar = toStar;
    }

    public List<ChipFormationVo> getChipFormationVos() {
        return chipFormationVos;
    }

    public void setChipFormationVos(List<ChipFormationVo> chipFormationVos) {
        this.chipFormationVos = chipFormationVos;
    }
}
