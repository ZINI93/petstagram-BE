package com.zini93.petstagram.domain.like.dto;

import com.zini93.petstagram.domain.post.entity.Post;
import com.zini93.petstagram.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeResponseDto {

    private User user;
    private Post post;


    @Builder
    public LikeResponseDto(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
