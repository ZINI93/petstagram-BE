package com.zini93.petstagram.domain.comment.repository;

import com.zini93.petstagram.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  Optional<Comment> findByUserUserUuidAndCommentUuid(String userUuid, String commentUuid);
}