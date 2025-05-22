package com.zini93.petstagram.domain.comment.dto;

import com.zini93.petstagram.domain.post.entity.Post;
import com.zini93.petstagram.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentRequestDto {

    private String content;


    @Builder
    public CommentRequestDto(String content) {
        this.content = content;
    }
}
