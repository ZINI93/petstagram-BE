package com.zini93.petstagram.domain.follow.entity;


import com.zini93.petstagram.domain.BaseEntity;
import com.zini93.petstagram.domain.TimeBaseEntity;
import com.zini93.petstagram.domain.follow.dto.FollowResponseDto;
import com.zini93.petstagram.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "follows")
@Entity
public class Follow extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id", nullable = false)
    private Long followId;

    @Column(name = "follow_uuid", nullable = false)
    private String followUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    private User following;



    @Builder
    public Follow(String followUuid, User follower, User following) {
        this.followUuid = UUID.randomUUID().toString();
        this.follower = follower;
        this.following = following;
    }


    public FollowResponseDto toResponse(){
        return FollowResponseDto.builder()
                .followUuid(this.getFollowUuid())
                .followerUUid(this.getFollower().getUserUuid())
                .followingUuid(this.getFollowing().getUserUuid())
                .build();
    }
}
