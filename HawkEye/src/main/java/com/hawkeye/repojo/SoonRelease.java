package com.hawkeye.repojo;

import java.sql.Timestamp;

public class SoonRelease {
    private Integer id;
    private String movieMainTitle;
    private Timestamp releaseTime;
    private Integer year;
    private String defaultImg;
    private Integer regionId;
    private String regionName;
    private Integer typeId;
    private String typeName;
    private Integer thinkSee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieMainTitle() {
        return movieMainTitle;
    }

    public void setMovieMainTitle(String movieMainTitle) {
        this.movieMainTitle = movieMainTitle;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getThinkSee() {
        return thinkSee;
    }

    public void setThinkSee(Integer thinkSee) {
        this.thinkSee = thinkSee;
    }
}
