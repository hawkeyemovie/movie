package com.hawkeye.pojo;


public class MovieRelationType {

  private Integer id;
  private Integer movieTypeId;
  private Integer movieId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getMovieTypeId() {
    return movieTypeId;
  }

  public void setMovieTypeId(Integer movieTypeId) {
    this.movieTypeId = movieTypeId;
  }


  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

}
