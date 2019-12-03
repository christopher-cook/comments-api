package com.example.model;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.model.EmailMessageToSend;
import com.example.commentsapi.model.Post;
import com.example.commentsapi.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class GlobalModelTest {

    @InjectMocks
    Comment comment;

    @InjectMocks
    Post post;

    @InjectMocks
    User user;

    @InjectMocks
    EmailMessageToSend email;

    @Before
    public void init() {
        Comment comment = new Comment();
        comment.setPost_id(2L);
        comment.setUser_id(1L);
        comment.setCommentId(3L);
       comment.setText("test comment");

       Post post = new Post(2L, "test post");
       User user = new User("test@email.com", "chris");

       EmailMessageToSend email = new EmailMessageToSend();
       email.setAuthorEmail("test@email.com");
       email.setCommentAuthorName("chris");
       email.setCommentText("my first comment");
       email.setPostTitle("my post title");
    }

    @Test
    public void globalFetch_Success() {
        comment.getId();
        comment.getText();
        comment.getPost_id();
        comment.getUser_id();
        comment.setId(2L);

        post.getUser_id();
        post.getTitle();

        user.getEmail();
        user.getUsername();

        email.getAuthorEmail();
        email.getCommentAuthorName();
        email.getCommentText();
        email.getPostTitle();
    }
}
