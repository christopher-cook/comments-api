package com.example.commentsapi.model;

public class Post {
  private Long user_id;
  private String title;

  public Post(Long user_id, String title) {
    this.user_id = user_id;
    this.title = title;
  }

  public Long getUser_id() {
    return user_id;
  }

  public String getTitle() {
    return title;
  }
}
