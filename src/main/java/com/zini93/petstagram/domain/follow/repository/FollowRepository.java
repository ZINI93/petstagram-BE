package com.zini93.petstagram.domain.follow.repository;

import com.zini93.petstagram.domain.follow.dto.FollowResponseDto;
import com.zini93.petstagram.domain.follow.entity.Follow;
import com.zini93.petstagram.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {

  Optional<Follow> findByFollowerUuidAndUserFollowingUuid(String followUuid, String followingUuid);
  Optional<Follow> findByFollowUuid(String followUuid);
  Boolean existsByFollowerAndFollowing(User follower, User following);
  void deleteByUserUserUuidAndUserUserUuid(String followerUuid, String followingUuid);

}