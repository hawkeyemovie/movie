package com.hawkeye.pojo;


public class User {

  private Integer id;
  private String name;
  private String phone;
  private Integer sex;
  private java.sql.Date birthday;
  private Integer lifeStateId;
  private Integer interestId;
  private String personalizedSignature;
  private Integer isHeadPortrait;
  private String headPortraitRoute;
  private String password;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }


  public java.sql.Date getBirthday() {
    return birthday;
  }

  public void setBirthday(java.sql.Date birthday) {
    this.birthday = birthday;
  }


  public Integer getLifeStateId() {
    return lifeStateId;
  }

  public void setLifeStateId(Integer lifeStateId) {
    this.lifeStateId = lifeStateId;
  }


  public Integer getInterestId() {
    return interestId;
  }

  public void setInterestId(Integer interestId) {
    this.interestId = interestId;
  }


  public String getPersonalizedSignature() {
    return personalizedSignature;
  }

  public void setPersonalizedSignature(String personalizedSignature) {
    this.personalizedSignature = personalizedSignature;
  }


  public Integer getIsHeadPortrait() {
    return isHeadPortrait;
  }

  public void setIsHeadPortrait(Integer isHeadPortrait) {
    this.isHeadPortrait = isHeadPortrait;
  }


  public String getHeadPortraitRoute() {
    return headPortraitRoute;
  }

  public void setHeadPortraitRoute(String headPortraitRoute) {
    this.headPortraitRoute = headPortraitRoute;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
