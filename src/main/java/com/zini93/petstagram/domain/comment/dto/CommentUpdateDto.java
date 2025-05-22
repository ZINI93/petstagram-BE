package com.zini93.petstagram.domain.comment.dto;

import com.zini93.petstagram.domain.post.entity.Post;
import com.zini93.petstagram.domain.user.entity.User;
import lombok.Getter;

@Getter
public class CommentUpdateDto {

    private String content;

    public CommentUpdateDto(String content) {
        this.content = content;
    }
}
