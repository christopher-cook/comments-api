package com.example.controller;


import com.example.commentsapi.controller.CommentController;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.service.CommentService;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@SpringBootTest
public class CommentControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private MockMvc mockMvc;

    @InjectMocks
    private CommentController commentController;

    @InjectMocks
    Comment comment;

    @Mock
    private CommentService commentService;

    List<Comment> commentList;

    @Before
    public void init() {

        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();

        comment.setUser_id(1L);
        comment.setPost_id(2L);
        comment.setCommentId(1L);
        comment.setText("test text");

    }

//    @Test
//    public void createComment_NewComment_Success() throws Exception {
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/comment/1")
//                .contentType(MediaType.APPLICATION_JSON).content(createJson("comment"));
//
//            when(commentService.createComment(anyString(), anyString(), any())).thenReturn(comment);
//
//
//        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
//                .andExpect(content().json("{\"commentId\":3,\"text\":\"test text\"}")).andReturn();
//        System.out.println(requestBuilder);
//        System.out.println(result.getResponse().getContentAsString());
//    }

//    @Test
//    public void deleteComment_ById_Success() throws Exception {
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/comment/1");
//
////        when(commentService.deleteComment(anyString(), anyLong())).thenReturn(2L);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andExpect(content().string("2")).andReturn();
//        System.out.println(result.getResponse().getContentAsString());
//    }
//
    private String createJson(String text) {

        return "{\"text\":\"" + text + "\"}";
    }
}
