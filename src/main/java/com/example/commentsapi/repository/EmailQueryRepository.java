package com.example.commentsapi.repository;

import com.example.commentsapi.model.Post;
import com.example.commentsapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmailQueryRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public User getUserById(Long id){
    String sql = "SELECT * FROM users WHERE id = ?";

    return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
        new User(
            rs.getString("email"),
            rs.getString("username")
        ));
  }

  public Post getPostByIdForUserId(Long id){
    String sql = "SELECT * FROM posts WHERE id = ?";

    return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
        new Post(
            rs.getLong("user_id"),
            rs.getString("title")
        ));
  }

}
