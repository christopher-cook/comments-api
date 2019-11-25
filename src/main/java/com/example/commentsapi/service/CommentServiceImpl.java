package com.example.commentsapi.service;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment createComment(String userId, String postId, Comment comment) {
        Long user_id = Long.parseLong(userId);
        Long post_id = Long.parseLong(postId);

        comment.setUser_id(user_id);
        comment.setPost_id(post_id);

        return commentRepository.save(comment);
    }

    @Override
    public Long deleteComment(Long commentId) throws EntityNotFoundException {
        try {
            commentRepository.deleteById(commentId);
            return commentId;
        } catch (Exception e){
            throw new EntityNotFoundException("Comment does not exist!");
        }
    }
}
