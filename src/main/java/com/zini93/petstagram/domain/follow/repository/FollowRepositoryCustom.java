package com.zini93.petstagram.domain.follow.repository;

import com.zini93.petstagram.domain.follow.dto.FollowResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowRepositoryCustom {


    Page<FollowResponseDto> followerList(String userUuid, String followerUuid, String nickname, Pageable pageable);
    Page<FollowResponseDto> followingList(String userUuid, String following, String nickname, Pageable pageable);

}
