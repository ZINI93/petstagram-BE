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
    private String postUuid;
    private String userUuid;
    private String content;

    @Builder
    public CommentResponseDto(String commentUuid, String postUuid, String userUuid, String content) {
        this.commentUuid = commentUuid;
        this.postUuid = postUuid;
        this.userUuid = userUuid;
        this.content = content;
    }
}
