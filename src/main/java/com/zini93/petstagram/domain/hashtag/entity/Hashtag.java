package com.zini93.petstagram.domain.hashtag.entity;


import com.zini93.petstagram.domain.hashtag.dto.HashtagRequestDto;
import com.zini93.petstagram.domain.hashtag.dto.HashtagResponseDto;
import com.zini93.petstagram.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Table(name = "hashtags")
@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hash_tags", nullable = false)
    private Long hashtagId;

    @Column(name = "hashtag_uuid", nullable = false, updatable = false, unique = true)
    private String hashtagUuid;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;


    @Builder
    public Hashtag(String hashtagUuid, String name, User user) {
        this.hashtagUuid = UUID.randomUUID().toString();
        this.name = name;
        this.user = user;
    }

    public HashtagResponseDto toResponse(){
        return HashtagResponseDto.builder()
                .hashtagUuid(this.hashtagUuid)
                .userUuid(user.getUserUuid())
                .name(this.name)
                .build();
    }
}
