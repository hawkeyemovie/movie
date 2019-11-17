package com.hawkeye.vo;

import java.util.List;

import com.hawkeye.vo.CinemaServiceVo;

public class CinemaVo {

    private Integer id;
    private String cinemaName;
    private String detailedAddress;
    private String cinemaLandline;
    private String imgRoute;
    private List<CinemaServiceVo> cinemaServiceVos;
    private List<CinemaMovieVo> cinemaMovieVos;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }


    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }


    public String getCinemaLandline() {
        return cinemaLandline;
    }

    public void setCinemaLandline(String cinemaLandline) {
        this.cinemaLandline = cinemaLandline;
    }

    public String getImgRoute() {
        return imgRoute;
    }

    public void setImgRoute(String imgRoute) {
        this.imgRoute = imgRoute;
    }

    public List<CinemaServiceVo> getCinemaServiceVos() {
        return cinemaServiceVos;
    }

    public void setCinemaServiceVos(List<CinemaServiceVo> cinemaServiceVos) {
        this.cinemaServiceVos = cinemaServiceVos;
    }

    public List<CinemaMovieVo> getCinemaMovieVos() {
        return cinemaMovieVos;
    }

    public void setCinemaMovieVos(List<CinemaMovieVo> cinemaMovieVos) {
        this.cinemaMovieVos = cinemaMovieVos;
    }
}
