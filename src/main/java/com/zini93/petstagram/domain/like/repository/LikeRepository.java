package com.zini93.petstagram.domain.like.repository;

import com.zini93.petstagram.domain.like.entity.Like;
import com.zini93.petstagram.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserUserUuidAndLikeUuid(String userUuid, String likeUuid);

    Like countByPost(Post post);
}