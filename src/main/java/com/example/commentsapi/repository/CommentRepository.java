package com.example.commentsapi.repository;

import com.example.commentsapi.model.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.post_id = :post_id")
    public void deleteAllByPost_Id(Long post_id);

}
