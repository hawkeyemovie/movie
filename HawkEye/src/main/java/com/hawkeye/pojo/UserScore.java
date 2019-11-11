package com.hawkeye.pojo;


public class UserScore {

  private Integer id;
  private double score;
  private Integer isBuyTickets;
  private String content;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }


  public Integer getIsBuyTickets() {
    return isBuyTickets;
  }

  public void setIsBuyTickets(Integer isBuyTickets) {
    this.isBuyTickets = isBuyTickets;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
