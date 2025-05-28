package com.zini93.petstagram.domain.hashtag.dto;

import com.zini93.petstagram.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class HashtagRequestDto {

    private String hashtagUuid;
    private String name;
    private String userUuid;


    @Builder
    public HashtagRequestDto(String hashtagUuid, String name, String userUuid) {
        this.hashtagUuid = hashtagUuid;
        this.name = name;
        this.userUuid = userUuid;
    }
}
