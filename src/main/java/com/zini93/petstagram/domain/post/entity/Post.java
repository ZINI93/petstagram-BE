package com.zini93.petstagram.domain.post.entity;

import com.zini93.petstagram.domain.BaseEntity;
import com.zini93.petstagram.domain.post.dto.PostResponseDto;
import com.zini93.petstagram.domain.post.dto.PostUpdateDto;
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
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false, updatable = false, unique = true)
    private Long postId;

    @Column(name = "post_uuid", nullable = false, updatable = false, unique = true)
    private String postUuid;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "post_image_url")
    private String postImageUrl;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Post(String postUuid, User user, String postImageUrl, String content) {
        this.postUuid = UUID.randomUUID().toString();
        this.user = user;
        this.postImageUrl = postImageUrl;
        this.content = content;
    }


    public PostResponseDto toResponse() {

        return PostResponseDto.builder()
                .postUuid(this.postUuid)
                .user(user)
                .postImageUrl(this.postImageUrl)
                .content(this.content)
                .build();
    }


    public void updatePost(String postImageUrl, String content) {
        this.postImageUrl = postImageUrl;
        this.content = content;
    }
}
