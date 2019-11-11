package com.hawkeye.pojo;


public class MovieTicket {

  private Integer id;
  private String  order;
  private String ticketCode;
  private String qrcodeRoute;
  private Integer movieId;
  private Integer cinemaId;
  private Integer movieHallId;
  private double ticketPrice;
  private java.util.Date projectionTime;
  private Integer payState;
  private java.util.Date foundTime;
  private java.util.Date modifyTime;
  private Integer uId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }


  public String getTicketCode() {
    return ticketCode;
  }

  public void setTicketCode(String ticketCode) {
    this.ticketCode = ticketCode;
  }


  public String getQrcodeRoute() {
    return qrcodeRoute;
  }

  public void setQrcodeRoute(String qrcodeRoute) {
    this.qrcodeRoute = qrcodeRoute;
  }


  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }


  public Integer getCinemaId() {
    return cinemaId;
  }

  public void setCinemaId(Integer cinemaId) {
    this.cinemaId = cinemaId;
  }


  public Integer getMovieHallId() {
    return movieHallId;
  }

  public void setMovieHallId(Integer movieHallId) {
    this.movieHallId = movieHallId;
  }


  public double getTicketPrice() {
    return ticketPrice;
  }

  public void setTicketPrice(double ticketPrice) {
    this.ticketPrice = ticketPrice;
  }


  public java.util.Date getProjectionTime() {
    return projectionTime;
  }

  public void setProjectionTime(java.util.Date projectionTime) {
    this.projectionTime = projectionTime;
  }


  public Integer getPayState() {
    return payState;
  }

  public void setPayState(Integer payState) {
    this.payState = payState;
  }


  public java.util.Date getFoundTime() {
    return foundTime;
  }

  public void setFoundTime(java.util.Date foundTime) {
    this.foundTime = foundTime;
  }


  public java.util.Date getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(java.util.Date modifyTime) {
    this.modifyTime = modifyTime;
  }


  public Integer getUId() {
    return uId;
  }

  public void setUId(Integer uId) {
    this.uId = uId;
  }

}
