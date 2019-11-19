package com.hawkeye.pojo;


public class Seat {

  private Integer id;
  private Integer cinemaHallId;
  private String seatName;
  private Integer row;
  private Integer column;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getCinemaHallId() {
    return cinemaHallId;
  }

  public void setCinemaHallId(Integer cinemaHallId) {
    this.cinemaHallId = cinemaHallId;
  }


  public String getSeatName() {
    return seatName;
  }

  public void setSeatName(String seatName) {
    this.seatName = seatName;
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

  public Seat(Integer id, Integer cinemaHallId, String seatName, Integer row, Integer column) {
    this.id = id;
    this.cinemaHallId = cinemaHallId;
    this.seatName = seatName;
    this.row = row;
    this.column = column;
  }

  public Seat(String seatName) {
    this.seatName = seatName;
  }

  public Seat() {
  }
}
