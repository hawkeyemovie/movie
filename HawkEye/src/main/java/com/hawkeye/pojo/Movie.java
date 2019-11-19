package com.hawkeye.pojo;


import java.io.Serializable;

public class Movie implements Serializable {

  private Integer id;
  private String movieMainTitle;
  private String movieSubheading;
  private Integer movieDuration;
  private String defaultImg;
  private String toStar;
  private String movieSynopsis;
  private Integer isUpperShelf;
  private Integer movieVersionId;
  private Integer movieRegionId;
  private java.util.Date releaseTime;
  private double score;

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

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


  public String getMovieSubheading() {
    return movieSubheading;
  }

  public void setMovieSubheading(String movieSubheading) {
    this.movieSubheading = movieSubheading;
  }


  public Integer getMovieDuration() {
    return movieDuration;
  }

  public void setMovieDuration(Integer movieDuration) {
    this.movieDuration = movieDuration;
  }


  public String getDefaultImg() {
    return defaultImg;
  }

  public void setDefaultImg(String defaultImg) {
    this.defaultImg = defaultImg;
  }


  public String getToStar() {
    return toStar;
  }

  public void setToStar(String toStar) {
    this.toStar = toStar;
  }


  public String getMovieSynopsis() {
    return movieSynopsis;
  }

  public void setMovieSynopsis(String movieSynopsis) {
    this.movieSynopsis = movieSynopsis;
  }


  public Integer getIsUpperShelf() {
    return isUpperShelf;
  }

  public void setIsUpperShelf(Integer isUpperShelf) {
    this.isUpperShelf = isUpperShelf;
  }


  public Integer getMovieVersionId() {
    return movieVersionId;
  }

  public void setMovieVersionId(Integer movieVersionId) {
    this.movieVersionId = movieVersionId;
  }


  public Integer getMovieRegionId() {
    return movieRegionId;
  }

  public void setMovieRegionId(Integer movieRegionId) {
    this.movieRegionId = movieRegionId;
  }


  public java.util.Date getReleaseTime() {
    return releaseTime;
  }

  public void setReleaseTime(java.util.Date releaseTime) {
    this.releaseTime = releaseTime;
  }

}
