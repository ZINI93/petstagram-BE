package com.zini93.petstagram.domain.post.dto;


import lombok.Getter;

@Getter
public class PostUpdateDto {

    private String postImageUrl;
    private String content;


    public PostUpdateDto(String postImageUrl, String content) {
        this.postImageUrl = postImageUrl;
        this.content = content;
    }
}

