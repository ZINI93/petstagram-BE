package com.zini93.petstagram.domain.like.service;

import com.zini93.petstagram.domain.like.dto.LikeResponseDto;
import com.zini93.petstagram.domain.like.entity.Like;
import com.zini93.petstagram.domain.like.repository.LikeRepository;
import com.zini93.petstagram.domain.post.entity.Post;
import com.zini93.petstagram.domain.post.repository.PostRepository;
import com.zini93.petstagram.domain.user.entity.User;
import com.zini93.petstagram.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LikeServiceImplTest {

    @Mock LikeRepository likeRepository;
    @Mock PostRepository postRepository;
    @Mock UserRepository userRepository;
    @InjectMocks LikeServiceImpl likeService;

    User user;
    Post post;
    Like like;

    @BeforeEach
    void setUP(){

        user = User.builder().userUuid(UUID.randomUUID().toString()).build();
        post = Post.builder().postUuid(UUID.randomUUID().toString()).build();
        like = Like.builder().likeUuid(UUID.randomUUID().toString()).user(user).post(post).build();

    }
    @Test
    void createLikeWithValidate() {

        //given
        when(userRepository.findByUserUuid(user.getUserUuid())).thenReturn(Optional.ofNullable(user));
        when(postRepository.findByPostUuid(post.getPostUuid())).thenReturn(Optional.ofNullable(post));
        when(likeRepository.save(any(Like.class))).thenReturn(like);

        //when
        LikeResponseDto result = likeService.createLikeWithValidate(user.getUserUuid(), post.getPostUuid());

        //then

        verify(userRepository,times(1)).findByUserUuid(user.getUserUuid());
        verify(postRepository,times(1)).findByPostUuid(post.getPostUuid());
        verify(likeRepository,times(1)).save(any(Like.class));

    }

    @Test
    void deleteLike() {
        //given
        when(likeRepository.findByUserUserUuidAndLikeUuid(user.getUserUuid(), like.getLikeUuid())).thenReturn(Optional.ofNullable(like));


        //when
        likeService.deleteLike(user.getUserUuid(), like.getLikeUuid());


        //then

    }

    @Test
    void getLikeCountByPostUuid() {
    }
}