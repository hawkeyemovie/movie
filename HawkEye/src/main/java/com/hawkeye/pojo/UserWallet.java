package com.hawkeye.pojo;


public class UserWallet {

  private Integer id;
  private Integer userId;
  private Integer paymentPwd;
  private String identityCard;
  private double walletBalance;
  private Integer walletState;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public Integer getPaymentPwd() {
    return paymentPwd;
  }

  public void setPaymentPwd(Integer paymentPwd) {
    this.paymentPwd = paymentPwd;
  }


  public String getIdentityCard() {
    return identityCard;
  }

  public void setIdentityCard(String identityCard) {
    this.identityCard = identityCard;
  }


  public double getWalletBalance() {
    return walletBalance;
  }

  public void setWalletBalance(double walletBalance) {
    this.walletBalance = walletBalance;
  }


  public Integer getWalletState() {
    return walletState;
  }

  public void setWalletState(Integer walletState) {
    this.walletState = walletState;
  }

}
