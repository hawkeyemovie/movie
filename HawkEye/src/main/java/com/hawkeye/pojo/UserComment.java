package com.hawkeye.pojo;


public class UserComment {

  private Integer id;
  private Integer themeId;
  private Integer themeType;
  private Integer userId;
  private String commentContent;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getThemeId() {
    return themeId;
  }

  public void setThemeId(Integer themeId) {
    this.themeId = themeId;
  }


  public Integer getThemeType() {
    return themeType;
  }

  public void setThemeType(Integer themeType) {
    this.themeType = themeType;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getCommentContent() {
    return commentContent;
  }

  public void setCommentContent(String commentContent) {
    this.commentContent = commentContent;
  }

}
