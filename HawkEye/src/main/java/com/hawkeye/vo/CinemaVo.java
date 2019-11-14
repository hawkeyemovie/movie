package com.hawkeye.vo;


import com.hawkeye.pojo.CinemaService;

import java.util.List;

public class CinemaVo {

    private Integer id;
    private String cinemaName;
    private String detailedAddress;
    private String cinemaLandline;
    private String imgRoute;
    private List<CinemaService> cinemaServices;


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


    public List<CinemaService> getCinemaServices() {
        return cinemaServices;
    }

    public void setCinemaServices(List<CinemaService> cinemaServices) {
        this.cinemaServices = cinemaServices;
    }

    public String getImgRoute() {
        return imgRoute;
    }

    public void setImgRoute(String imgRoute) {
        this.imgRoute = imgRoute;
    }
}
