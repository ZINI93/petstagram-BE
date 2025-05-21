package com.zini93.petstagram.domain.post.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostRequestDto {

    private String postImageUrl;
    private String content;


    @Builder
    public PostRequestDto(String postImageUrl, String content) {
        this.postImageUrl = postImageUrl;
        this.content = content;
    }
}
