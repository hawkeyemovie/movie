package com.hawkeye.pojo;


public class UserReply {

  private Integer id;
  private Integer commentId;
  private Integer targetUserId;
  private Integer replyUserId;
  private String replyContent;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }


  public Integer getTargetUserId() {
    return targetUserId;
  }

  public void setTargetUserId(Integer targetUserId) {
    this.targetUserId = targetUserId;
  }


  public Integer getReplyUserId() {
    return replyUserId;
  }

  public void setReplyUserId(Integer replyUserId) {
    this.replyUserId = replyUserId;
  }


  public String getReplyContent() {
    return replyContent;
  }

  public void setReplyContent(String replyContent) {
    this.replyContent = replyContent;
  }

}
