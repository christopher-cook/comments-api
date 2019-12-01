package com.example.commentsapi.controller;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.service.CommentService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private Logger logger;

    @Autowired
    CommentService commentService;

    @PostMapping("/{postId}")
    public Comment createComment(@RequestHeader("userId") String userId, @PathVariable String postId, @RequestBody Comment comment){
        return commentService.createComment(userId, postId, comment);

    }

    @DeleteMapping("/{commentId}")
    public Long deleteComment(@PathVariable Long commentId) throws EntityNotFoundException {
//        logger.debug("deleting a comment");
        return commentService.deleteComment(commentId);
    }

}
