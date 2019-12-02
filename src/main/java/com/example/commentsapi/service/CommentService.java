package com.example.commentsapi.service;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;
import org.springframework.stereotype.Service;

public interface CommentService {
    /**
     *
     * @param userId String
     * @param postId String
     * @param comment Object
     * @return comment
     */
    public Comment createComment(String userId, String postId, Comment comment);

    public Long deleteComment(Long commentId) throws EntityNotFoundException;
}
