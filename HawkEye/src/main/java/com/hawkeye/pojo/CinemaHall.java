package com.hawkeye.pojo;


public class CinemaHall {

  private Integer id;
  private String cinemaHallName;
  private Integer cinemaId;
  private String layout;
  private Integer row;
  private Integer column;
  private Integer cinemaHallTypeId;

  public Integer getCinemaHallTypeId() {
    return cinemaHallTypeId;
  }

  public void setCinemaHallTypeId(Integer cinemaHallTypeId) {
    this.cinemaHallTypeId = cinemaHallTypeId;
  }

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

  public CinemaHall(String layout) {
    this.layout = layout;
  }

  public CinemaHall() {
  }

  public CinemaHall(String layout, Integer column) {
    this.layout = layout;
    this.column = column;
  }

  public CinemaHall(Integer id, String cinemaHallName, Integer cinemaId, String layout, Integer row, Integer column, Integer cinemaHallTypeId) {
    this.id = id;
    this.cinemaHallName = cinemaHallName;
    this.cinemaId = cinemaId;
    this.layout = layout;
    this.row = row;
    this.column = column;
    this.cinemaHallTypeId = cinemaHallTypeId;
  }
}
