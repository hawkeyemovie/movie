package com.hawkeye.pojo;


public class ActingStaff {

  private Integer id;
  private Integer movieId;
  private Integer filmmakerId;
  private Integer filmmakerTypeId;
  private String actor;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }


  public Integer getFilmmakerId() {
    return filmmakerId;
  }

  public void setFilmmakerId(Integer filmmakerId) {
    this.filmmakerId = filmmakerId;
  }


  public Integer getFilmmakerTypeId() {
    return filmmakerTypeId;
  }

  public void setFilmmakerTypeId(Integer filmmakerTypeId) {
    this.filmmakerTypeId = filmmakerTypeId;
  }


  public String getActor() {
    return actor;
  }

  public void setActor(String actor) {
    this.actor = actor;
  }

}
