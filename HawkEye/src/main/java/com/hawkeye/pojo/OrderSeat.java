package com.hawkeye.pojo;


public class OrderSeat {

  private Integer id;
  private String ticketOrder;
  private Integer seatId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getTicketOrder() {
    return ticketOrder;
  }

  public void setTicketOrder(String ticketOrder) {
    this.ticketOrder = ticketOrder;
  }


  public Integer getSeatId() {
    return seatId;
  }

  public void setSeatId(Integer seatId) {
    this.seatId = seatId;
  }

}
