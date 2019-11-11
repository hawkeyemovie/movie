package com.hawkeye.pojo;


public class ChipFormation {

  private Integer id;
  private java.util.Date startTime;
  private java.util.Date dispersionTime;
  private Integer movieId;
  private Integer cniemaHallId;
  private double price;
  private String languageVersion;
  private String chipFormationSeat;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public java.util.Date getStartTime() {
    return startTime;
  }

  public void setStartTime(java.util.Date startTime) {
    this.startTime = startTime;
  }


  public java.util.Date getDispersionTime() {
    return dispersionTime;
  }

  public void setDispersionTime(java.util.Date dispersionTime) {
    this.dispersionTime = dispersionTime;
  }


  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }


  public Integer getCniemaHallId() {
    return cniemaHallId;
  }

  public void setCniemaHallId(Integer cniemaHallId) {
    this.cniemaHallId = cniemaHallId;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public String getLanguageVersion() {
    return languageVersion;
  }

  public void setLanguageVersion(String languageVersion) {
    this.languageVersion = languageVersion;
  }


  public String getChipFormationSeat() {
    return chipFormationSeat;
  }

  public void setChipFormationSeat(String chipFormationSeat) {
    this.chipFormationSeat = chipFormationSeat;
  }

}
