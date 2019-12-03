package com.example.commentsapi.model;

public class EmailMessageToSend {
  private String authorEmail;
  private String commentAuthorName;
  private String commentText;
  private String postTitle;

  public EmailMessageToSend() {

  }

  public String getAuthorEmail() {
    return authorEmail;
  }

  public void setAuthorEmail(String authorEmail) {
    this.authorEmail = authorEmail;
  }

  public String getCommentAuthorName() {
    return commentAuthorName;
  }

  public void setCommentAuthorName(String commentAuthorName) {
    this.commentAuthorName = commentAuthorName;
  }

  public String getCommentText() {
    return commentText;
  }

  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }

  public String getPostTitle() {
    return postTitle;
  }

  public void setPostTitle(String postTitle) {
    this.postTitle = postTitle;
  }
}
