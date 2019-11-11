package com.hawkeye.pojo;


public class Filmmaker {

  private Integer id;
  private String filmmakerName;
  private String filmmakerAlias;
  private String filmmakerIntroduce;
  private String fNative;
  private java.sql.Date birthday;
  private Integer sex;
  private String defaultImg;
  private String nation;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getFilmmakerName() {
    return filmmakerName;
  }

  public void setFilmmakerName(String filmmakerName) {
    this.filmmakerName = filmmakerName;
  }


  public String getFilmmakerAlias() {
    return filmmakerAlias;
  }

  public void setFilmmakerAlias(String filmmakerAlias) {
    this.filmmakerAlias = filmmakerAlias;
  }


  public String getFilmmakerIntroduce() {
    return filmmakerIntroduce;
  }

  public void setFilmmakerIntroduce(String filmmakerIntroduce) {
    this.filmmakerIntroduce = filmmakerIntroduce;
  }


  public String getFNative() {
    return fNative;
  }

  public void setFNative(String fNative) {
    this.fNative = fNative;
  }


  public java.sql.Date getBirthday() {
    return birthday;
  }

  public void setBirthday(java.sql.Date birthday) {
    this.birthday = birthday;
  }


  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }


  public String getDefaultImg() {
    return defaultImg;
  }

  public void setDefaultImg(String defaultImg) {
    this.defaultImg = defaultImg;
  }


  public String getNation() {
    return nation;
  }

  public void setNation(String nation) {
    this.nation = nation;
  }

}
