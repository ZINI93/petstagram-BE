package com.zini93.petstagram.domain.post.dto;


import com.zini93.petstagram.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostResponseDto {

    private String postUuid;
    private com.zini93.petstagram.domain.user.entity.User user;
    private String postImageUrl;
    private String content;


    @Builder
    public PostResponseDto(String postUuid, User user, String postImageUrl, String content) {
        this.postUuid = postUuid;
        this.user = user;
        this.postImageUrl = postImageUrl;
        this.content = content;
    }
}
