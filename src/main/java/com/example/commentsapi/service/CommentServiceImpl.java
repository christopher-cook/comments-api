package com.example.commentsapi.service;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.model.EmailMessageToSend;
import com.example.commentsapi.model.Post;
import com.example.commentsapi.model.User;
import com.example.commentsapi.repository.CommentRepository;
import com.example.commentsapi.repository.EmailQueryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

/**
 * @author Christopher Cook
 * @author Qin Zhu
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    EmailQueryRepository emailQueryRepository;

    private ObjectMapper mapper = new ObjectMapper();
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

//      send message to email server
        sendEmailToOriginalPost(post_id, user_id, comment);

        return commentRepository.save(comment);
    }

    public void sendEmailToOriginalPost(Long postId, Long commentAuthorId, Comment comment) {
        Post post = emailQueryRepository.getPostByIdForUserId(postId);
        User postAuthor = emailQueryRepository.getUserById(post.getUser_id());
        User commentAuthor = emailQueryRepository.getUserById(commentAuthorId);

        EmailMessageToSend messageToSend = new EmailMessageToSend();

        messageToSend.setAuthorEmail(postAuthor.getEmail());
        messageToSend.setCommentAuthorName(commentAuthor.getUsername());
        messageToSend.setCommentText(comment.getText());
        messageToSend.setPostTitle(post.getTitle());

        System.out.println("sending rabbit message");

        try {
            String toSend = mapper.writeValueAsString(messageToSend);
            rabbitTemplate.convertAndSend("sendEmailToOriginalPoster", toSend);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
