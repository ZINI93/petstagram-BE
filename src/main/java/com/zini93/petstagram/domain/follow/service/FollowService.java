package com.zini93.petstagram.domain.follow.service;

import com.zini93.petstagram.domain.follow.dto.FollowResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowService {



    FollowResponseDto followUser(String followerUuid, String followingUuid);
    Page<FollowResponseDto> getFollower(String userUuid, String followerUuid, String nickName, Pageable pageable);
    Page<FollowResponseDto> getFollowing(String userUuid, String followingUuid, String nickName, Pageable pageable);
    void unFollow(String follower, String followUuid);
}
