package com.example.commentsapi.service;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Christopher Cook
 * @author Qin Zhu
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    /**
     *
     * @param userId Long
     * @param postId Long
     * @param comment Comment object
     * @return new Comment
     */
    @Override
    public Comment createComment(String userId, String postId, Comment comment) {
        Long user_id = Long.parseLong(userId);
        Long post_id = Long.parseLong(postId);

        comment.setUser_id(user_id);
        comment.setPost_id(post_id);

        return commentRepository.save(comment);
    }

    /**
     *
     * @param commentId Long commentId
     * @return commentId when deleted
     * @throws EntityNotFoundException when commentId does not exist
     */
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
