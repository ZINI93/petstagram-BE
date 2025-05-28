package com.zini93.petstagram.domain.hashtag.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HashtagResponseDto {

    private String hashtagUuid;
    private String name;
    private String userUuid;


    @Builder
    public HashtagResponseDto(String hashtagUuid, String name, String userUuid) {
        this.hashtagUuid = hashtagUuid;
        this.name = name;
        this.userUuid = userUuid;
    }
}


