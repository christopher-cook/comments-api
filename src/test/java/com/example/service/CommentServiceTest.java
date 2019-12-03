package com.example.service;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;

import com.example.commentsapi.model.Post;
import com.example.commentsapi.model.User;
import com.example.commentsapi.repository.CommentRepository;
import com.example.commentsapi.repository.EmailQueryRepository;
import com.example.commentsapi.service.CommentServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class CommentServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    User user;

    @InjectMocks
    private Comment comment;

    @InjectMocks
    public CommentServiceImpl commentService;

    @Mock
    private Post post;

    @Mock
    CommentRepository commentRepository;

    @Mock
    EmailQueryRepository emailQueryRepository;

    @Mock
    RabbitTemplate rabbitTemplate;

    @Before
    public void init() {
        Comment comment = new Comment();

        comment.setCommentId(2L);
        comment.setUser_id(2L);
        comment.setPost_id(1L);
        comment.setText("testText");
        commentRepository.save(comment);
    }

    @Test
    public void createComment_NewComm_Success() {

        when(commentRepository.save(any())).thenReturn(comment);
        doNothing().when(rabbitTemplate).convertAndSend(anyString(), (Object) any());
        when(emailQueryRepository.getUserById(anyLong())).thenReturn(user);
        when(emailQueryRepository.getPostByIdForUserId(anyLong())).thenReturn(post);

        Comment actualComment = commentService.createComment("2", "1", comment);

        assertEquals(actualComment, comment);
    }

    @Test
    public void deleteComm_ById_Success() throws EntityNotFoundException {

        commentRepository.deleteById(anyLong());
        Long deletedCommentId = commentService.deleteComment(2L);

    }

}
