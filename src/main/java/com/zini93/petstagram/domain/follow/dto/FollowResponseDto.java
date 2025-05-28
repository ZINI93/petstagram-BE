package com.zini93.petstagram.domain.follow.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.zini93.petstagram.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class FollowResponseDto {

    private String followUuid;
    private String followerUUid;
    private String followingUuid;


    @Builder
    public FollowResponseDto(String followUuid, String followerUUid, String followingUuid) {
        this.followUuid = followUuid;
        this.followerUUid = followerUUid;
        this.followingUuid = followingUuid;
    }

    @QueryProjection
    public FollowResponseDto(String followUuid, String followerUUid) {
        this.followUuid = followUuid;
        this.followerUUid = followerUUid;
    }
}
