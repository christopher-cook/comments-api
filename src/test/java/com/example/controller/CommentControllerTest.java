package com.example.controller;

import com.example.commentsapi.bean.UserBean;
import com.example.commentsapi.controller.CommentController;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import com.example.commentsapi.service.CommentService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {CommentController.class})
public class CommentControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private MockMvc mockMvc;

    @InjectMocks
    private CommentController commentController;

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    Comment comment;

    @Mock
    private CommentService commentService;

    @InjectMocks
    UserBean user;

    List<Comment> commentList;

    @Before
    public void init() {

        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();

        comment.setUser_id(1L);
        comment.setPost_id(2L);
        comment.setCommentId(1L);
        comment.setText("test text");

        user.setEmail("chris@test.com");
        user.setId(1L);
        user.setUsername("chris");
        user.setPassword("testPass");

    }

    @Test
    public void createNewComment_Success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/1").header("userId",1L).header("username","chris")
                .contentType(MediaType.APPLICATION_JSON).content(createJson("comment"));

                when(commentService.createComment(anyString(), anyString(), any())).thenReturn(comment);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().json("{\"text\":\"test text\"}")).andReturn();

    }

    @Test
    public void deleteComment_ById_Success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/1");
        try {
            when(commentService.deleteComment(anyLong())).thenReturn(2L);
        } catch (Exception e) {
            e.getMessage();
        }
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(content().string("2")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    private String createJson(String text) {

        return "{\"text\":\"" + text + "\"}";
    }
}
