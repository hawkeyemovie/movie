package com.hawkeye.pojo;

public class OrderSeat {

  private Integer id;
  private Integer movieTicketId;
  private Integer seatId;
  private Seat seat;

  public Seat getSeat() {
    return seat;
  }

  public void setSeat(Seat seat) {
    this.seat = seat;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getTicketOrder() {
    return movieTicketId;
  }

  public void setTicketOrder(Integer ticketOrder) {
    this.movieTicketId = ticketOrder;
  }


  public Integer getSeatId() {
    return seatId;
  }

  public void setSeatId(Integer seatId) {
    this.seatId = seatId;
  }

  public OrderSeat(Integer id, Integer movieTicketId, Integer seatId, Seat seat) {
    this.id = id;
    this.movieTicketId = movieTicketId;
    this.seatId = seatId;
    this.seat = seat;
  }

  public OrderSeat() {
  }
}
