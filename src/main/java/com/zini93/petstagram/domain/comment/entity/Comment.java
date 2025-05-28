package com.zini93.petstagram.domain.comment.entity;

import com.zini93.petstagram.domain.BaseEntity;
import com.zini93.petstagram.domain.comment.dto.CommentResponseDto;
import com.zini93.petstagram.domain.post.entity.Post;
import com.zini93.petstagram.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long commentId;


    @Column(name = "comment_uuid", nullable = false, updatable = false)
    private String commentUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String content;


    @Builder
    public Comment(String commentUuid, Post post, User user, String content) {
        this.commentUuid = UUID.randomUUID().toString();
        this.post = post;
        this.user = user;
        this.content = content;
    }

    public CommentResponseDto toResponse(){
        return CommentResponseDto.builder()
                .commentUuid(this.commentUuid)
                .postUuid(this.post.getPostUuid())
                .userUuid(this.user.getUserUuid())
                .content(this.content)
                .build();
    }


    public void updateComment(String content) {
        this.content = content;
    }
}
