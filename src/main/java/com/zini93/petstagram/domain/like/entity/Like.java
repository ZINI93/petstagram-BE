package com.zini93.petstagram.domain.like.entity;


import com.zini93.petstagram.domain.like.dto.LikeResponseDto;
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
@Table(name = "likes")
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long likeId;

    @Column(name = "like_uuid", nullable = false, updatable = false, unique = true)
    private String likeUuid = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;


    @Builder
    public Like(String likeUuid, User user, Post post) {
        this.likeUuid = likeUuid;
        this.user = user;
        this.post = post;
    }

    public LikeResponseDto toResponse(){
        return new LikeResponseDto(this.user,this.post);
    }

}
