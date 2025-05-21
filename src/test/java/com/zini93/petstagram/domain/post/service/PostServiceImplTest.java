package com.zini93.petstagram.domain.post.service;

import com.zini93.petstagram.domain.post.dto.PostRequestDto;
import com.zini93.petstagram.domain.post.dto.PostResponseDto;
import com.zini93.petstagram.domain.post.dto.PostUpdateDto;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {


    @Mock
    UserRepository userRepository;
    @Mock
    PostRepository postRepository;
    @InjectMocks
    PostServiceImpl postService;

    PostRequestDto requestDto;
    Post post;
    User user;

    @BeforeEach
    void setUp() {

        user = User.builder().userUuid(UUID.randomUUID().toString()).build();

        requestDto = PostRequestDto.builder().
                postImageUrl("www.image.com").
                content("cute cat").
                build();

        post = new Post(UUID.randomUUID().toString(), user, requestDto.getPostImageUrl(), requestDto.getContent());
    }

    @Test
    void createPostWithValidate() {

        //given
        when(userRepository.findByUserUuid(user.getUserUuid())).thenReturn(Optional.ofNullable(user));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        //when
        PostResponseDto result = postService.createPostWithValidate(user.getUserUuid(), requestDto);

        //then
        assertNotNull(result);
        assertEquals(post.getPostUuid(),result.getPostUuid());
        assertEquals(post.getContent(), result.getContent());

        verify(userRepository, times(1)).findByUserUuid(user.getUserUuid());
        verify(postRepository, times(1)).save(any(Post.class));

    }

    @Test
    void getPostInfo() {

        //given
        when(postRepository.findByUserUserUuidAndPostUuid(user.getUserUuid(), post.getPostUuid())).thenReturn(Optional.ofNullable(post));

        //when
        PostResponseDto result = postService.getPostInfo(user.getUserUuid(), post.getPostUuid());

        //then
        assertNotNull(result);
        assertEquals(post.getContent(),result.getContent());
        assertEquals(post.getPostImageUrl(),result.getPostImageUrl());

        verify(postRepository,times(1)).findByUserUserUuidAndPostUuid(user.getUserUuid(),post.getPostUuid());
    }

    @Test
    void getAllPost() {
    }

    @Test
    void updatePostWithValidate() {
        //given
        PostUpdateDto updatePost = new PostUpdateDto("www.image2.com", "cute cat");
        when(postRepository.findByUserUserUuidAndPostUuid(user.getUserUuid(), post.getPostUuid())).thenReturn(Optional.ofNullable(post));

        //when
        PostResponseDto result = postService.updatePostWithValidate(user.getUserUuid(), post.getPostUuid(), updatePost);

        assertNotNull(result);
        assertEquals(updatePost.getPostImageUrl(),result.getPostImageUrl());
        assertEquals(updatePost.getContent(),result.getContent());

        verify(postRepository,times(1)).findByUserUserUuidAndPostUuid(user.getUserUuid(),post.getPostUuid());

    }

    @Test
    void deletePost() {
        //given
        when(postRepository.findByUserUserUuidAndPostUuid(user.getUserUuid(), post.getPostUuid())).thenReturn(Optional.ofNullable(post));

        //when
        postService.deletePost(user.getUserUuid(),post.getPostUuid());

        //then
    }
}