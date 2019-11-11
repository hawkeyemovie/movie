package com.hawkeye.pojo;


public class CinemaHall {

  private Integer id;
  private String cinemaHallName;
  private Integer cinemaId;
  private String layout;
  private Integer row;
  private Integer column;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getCinemaHallName() {
    return cinemaHallName;
  }

  public void setCinemaHallName(String cinemaHallName) {
    this.cinemaHallName = cinemaHallName;
  }


  public Integer getCinemaId() {
    return cinemaId;
  }

  public void setCinemaId(Integer cinemaId) {
    this.cinemaId = cinemaId;
  }


  public String getLayout() {
    return layout;
  }

  public void setLayout(String layout) {
    this.layout = layout;
  }


  public Integer getRow() {
    return row;
  }

  public void setRow(Integer row) {
    this.row = row;
  }


  public Integer getColumn() {
    return column;
  }

  public void setColumn(Integer column) {
    this.column = column;
  }

}
