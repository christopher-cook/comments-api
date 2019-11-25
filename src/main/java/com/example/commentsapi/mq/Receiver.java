package com.example.commentsapi.mq;

import com.example.commentsapi.repository.CommentRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    CommentRepository commentRepository;

    @RabbitListener(queues = "deleteCommentsByPostId")
    @RabbitHandler
    public void receive(String message) {
        System.out.println(message);
        commentRepository.deleteAllByPost_Id(Long.parseLong(message));
    }

}
