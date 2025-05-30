package com.zini93.petstagram.domain.hashtag.repository;

import com.zini93.petstagram.domain.hashtag.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

  Optional<Hashtag> findByUserUserUuidAndHashtagUuid(String userUuid, String hashtagUuid);
}