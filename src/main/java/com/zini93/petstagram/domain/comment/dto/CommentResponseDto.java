package com.zini93.petstagram.domain.comment.dto;

import com.zini93.petstagram.domain.post.entity.Post;
import com.zini93.petstagram.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CommentResponseDto {

    private String commentUuid;
    private Post post;
    private User user;
    private String content;

    @Builder
    public CommentResponseDto(String commentUuid, Post post, User user, String content) {
        this.commentUuid = commentUuid;
        this.post = post;
        this.user = user;
        this.content = content;
    }
}
