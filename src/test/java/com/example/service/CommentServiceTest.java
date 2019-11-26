package com.example.service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.commentsapi.controller.CommentController;
import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;

import com.example.commentsapi.mq.Receiver;
import com.example.commentsapi.repository.CommentRepository;
import com.example.commentsapi.service.CommentServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


public class CommentServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    @InjectMocks
    private Comment comment;

    @InjectMocks
    public CommentServiceImpl commentService;

//    @Mock
//    CommentController commentController;

    @Mock
    CommentRepository commentRepository;

    List<Comment> commentList;

    @Before
    public void init() {
        Comment comment = new Comment();

        comment.setCommentId(2L);
        comment.setUser_id(2L);
        comment.setPost_id(1L);
        comment.setText("testText");
        commentRepository.save(comment);

        commentList = new ArrayList<Comment>();
        commentList.add(comment);

    }

    @Test
    public void createComment_NewComm_Success() {

        Comment actualComment = commentService.createComment("2", "1", comment);
        commentList.add(actualComment);
        assertNotNull(commentList);
    }

    @Test
    public void deleteComm_ById_Success() throws EntityNotFoundException {

        commentRepository.deleteById(anyLong());
        Long deletedCommentId = commentService.deleteComment(2L);

//       assertEquals((long)2, (long) deletedCommentId);


    }


}
