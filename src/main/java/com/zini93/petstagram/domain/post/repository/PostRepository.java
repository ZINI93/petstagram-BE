package com.zini93.petstagram.domain.post.repository;

import com.zini93.petstagram.domain.post.dto.PostResponseDto;
import com.zini93.petstagram.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByUserUserUuidAndPostUuid(String userUuid, String postUuid);
    Optional<Post> findByPostUuid(String postUuid);

}